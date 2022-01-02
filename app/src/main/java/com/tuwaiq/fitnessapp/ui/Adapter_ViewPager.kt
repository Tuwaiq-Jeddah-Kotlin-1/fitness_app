package com.tuwaiq.fitnessapp.ui

import  android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.view.size
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.tuwaiq.fitnessapp.R
import com.tuwaiq.fitnessapp.data.Exercise
import com.tuwaiq.fitnessapp.databinding.ExerciseItemBinding
import com.tuwaiq.fitnessapp.databinding.FragmentPlayExerciseItemBinding
import com.tuwaiq.fitnessapp.databinding.WorkoutItemBinding

class Adapter_ViewPager(val playList: List<Exercise>, val viewpager: ViewPager2) :
    RecyclerView.Adapter<Adapter_ViewPager.pagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): pagerViewHolder {
        val bind: FragmentPlayExerciseItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.fragment_play_exercise_item, parent, false
        )
        return pagerViewHolder(bind, viewpager)
    }

    override fun onBindViewHolder(holder: pagerViewHolder, position: Int) {
        val exercise: Exercise = playList[position]
        holder.bind(exercise)

    }

    override fun getItemCount(): Int {
        return playList.size
    }

    inner class pagerViewHolder(
        val binding: FragmentPlayExerciseItemBinding,
        val viewpager: ViewPager2
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(exercise: Exercise) {
            binding.playexercise = exercise
            binding.playExerciseImage.load(exercise.image_url)
            //set the timer
            var isStart: Boolean = true

            binding.tvBackHomePage.setOnClickListener {
                itemView.findNavController()
                    .navigate(R.id.action_playExercisesList_to_mainScreen2)
            }
            binding.TvReplay.setOnClickListener {
                viewpager.currentItem = 0
            }
            var endTime: Long = 10000
            var timeLeft: Long = 0
            // start the timer immediately
            var timer = object : CountDownTimer(endTime, 1000) {

                override fun onTick(millisUntilFinished: Long) {
                    Log.e("endtime", "current item $endTime")

                    timeLeft = millisUntilFinished
                    binding.tvRemainingTime.text = "${(millisUntilFinished / 1000)} s"
                }

                override fun onFinish() {
                    binding.tvRemainingTime.text = "done!"
                    val current = viewpager.currentItem
                    Log.e("finish", "current item $current")
                    viewpager.currentItem = current + 1
                    Log.e("after set finish", "childcount item ${viewpager.adapter?.itemCount}")
                    binding.TvReplay.isVisible = viewpager.adapter?.itemCount?.minus(1) == viewpager.currentItem

                }
            }.start()

            //stop the timer
            fun stopTimer() {
                timer.cancel()
                isStart = false
            }   // update the state of the timer

            //resume function to restart the Timer with remaining time
            fun resumeTimer() {
                timer = object : CountDownTimer(timeLeft, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        Log.e("endtime", "current item $endTime")
                        timeLeft = millisUntilFinished
                        binding.tvRemainingTime.text = "${(millisUntilFinished / 1000)} s"
                    }

                    override fun onFinish() {
                        binding.tvRemainingTime.text = "00"
                        val current = viewpager.currentItem
                        viewpager.currentItem = current + 1
                        Log.e("items count ", "kkk ${viewpager.adapter?.itemCount}")
                    }
                }.start()
                // update the state of the timer
                isStart = true
            }

            //onClick for start and stop the timer
            binding.floatingActionButton.setOnClickListener {
                if (!isStart) {
                    resumeTimer()
                    val drawable =
                        itemView.resources.getDrawable(R.drawable.ic_baseline_next_plan_24)
                    binding.floatingActionButton.setBackgroundDrawable(drawable)
                } else {
                    stopTimer()
                    val drawable =
                        itemView.resources.getDrawable(R.drawable.ic_baseline_play_arrow_24)
                    binding.floatingActionButton.setBackgroundDrawable(drawable)
                }
            }
        }

    }

}