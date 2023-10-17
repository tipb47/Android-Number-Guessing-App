package com.example.midterm

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.midterm.databinding.FragmentGameBottomBinding

/**
 * A simple [Fragment] subclass.
 * Use the [GameFragmentBottom.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragmentBottom : Fragment() {
    private var _binding: FragmentGameBottomBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBottomBinding.inflate(inflater, container, false)
        val view = binding.root

        //connect to SHARED view model
        val application = requireNotNull(this.activity).application
        val dao = ScoreDatabase.getInstance(application).scoreDao()
        val viewModelFactory = GameViewModelFactory(dao)
        viewModel = ViewModelProvider(requireParentFragment(), viewModelFactory)[GameViewModel::class.java]

        binding.viewModel = viewModel

        //keep track of attempts
        viewModel.attempts.observe(viewLifecycleOwner, Observer { attempts ->
            Log.d("GameFragmentBottom", "attempts observer triggered: $attempts")
            val attemptText = "Number of attempts: $attempts"
            binding.attemptsText.text = attemptText
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}