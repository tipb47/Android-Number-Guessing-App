package com.example.midterm

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.midterm.databinding.FragmentGameTopBinding

/**
 * A simple [Fragment] subclass.
 * Use the [GameFragmentTop.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragmentTop : Fragment() {
    private var _binding: FragmentGameTopBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameTopBinding.inflate(inflater, container, false)
        val view = binding.root

        //connect to SHARED view model
        val application = requireNotNull(this.activity).application
        val dao = ScoreDatabase.getInstance(application).scoreDao()
        val viewModelFactory = GameViewModelFactory(dao)
        viewModel = ViewModelProvider(requireParentFragment(), viewModelFactory)[GameViewModel::class.java]

        binding.viewModel = viewModel

        //submit guess button
        binding.okButton.setOnClickListener {
            //log number in Logcat for testing purposes.
            Log.d("number",viewModel.randomNumber.toString())
            viewModel.playerNameInput = binding.playerNameInput.text.toString()
            viewModel.submitGuess(binding.guessTextInput.text.toString())
        }

        //decrement by one button
        binding.minusButton.setOnClickListener {
            val currentNumber = binding.guessTextInput.text.toString().toIntOrNull()
            if (currentNumber != null) { //if not empty
                binding.guessTextInput.setText((currentNumber - 1).toString())
            }
        }
        //increment by one button
        binding.plusButton.setOnClickListener {
            val currentNumber = binding.guessTextInput.text.toString().toIntOrNull()
            if (currentNumber != null) { //if not empty
                binding.guessTextInput.setText((currentNumber + 1).toString())
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}