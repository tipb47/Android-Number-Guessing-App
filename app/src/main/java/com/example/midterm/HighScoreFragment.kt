package com.example.midterm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.midterm.databinding.FragmentHighScoreBinding

/**
 * A simple [Fragment] subclass.
 * Use the [HighScoreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class HighScoreFragment : Fragment() {
    private var _binding: FragmentHighScoreBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //binding
        _binding = FragmentHighScoreBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = ScoreDatabase.getInstance(application).scoreDao()

        //create viewmodel
        val viewModelFactory = ScoresViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory)[ScoresViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        fun yesPressed (noteId : Long) {
            binding.viewModel?.deleteScore(noteId)
        }

        //delete button, prompt pop up
        fun deleteClicked (scoreId : Long) {
            ConfirmDialogFragment(scoreId,::yesPressed).show(childFragmentManager,
                ConfirmDialogFragment.TAG)
        }

        val adapter = ScoreItemAdapter(::deleteClicked)

        binding.highScoreList.adapter = adapter

        viewModel.scores.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }

        //navigate back
        viewModel.navigateToMenu.observe(viewLifecycleOwner, Observer { navigate ->
            if (navigate) {
                view.findNavController()
                    .navigate(R.id.action_highScoreFragment_to_menuFragment)
                viewModel.onMenuNavigated()
            }
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}