package com.example.midterm

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.midterm.databinding.FragmentMenuBinding

/**
 * A simple [Fragment] subclass.
 * Use the [MenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class MenuFragment : Fragment() {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //binding
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        val view = binding.root

        val args: MenuFragmentArgs by navArgs()

        // get args from last game
        val playerName = args.playerName
        val score = args.score

        //set top text if game played beforehand
        if (playerName != "0" && score != "0") {
            val text = "$playerName score: $score\n\nPlay another game?"
            binding.welcomeText.text = text
        }

        //play game button
        binding.playGameButton.setOnClickListener {
            val action = MenuFragmentDirections.actionMenuFragmentToGameFragment()
            this.findNavController().navigate(action)

        }

        //view high scores button
        binding.highScoreButton.setOnClickListener {
            val action = MenuFragmentDirections.actionMenuFragmentToHighScoreFragment()
            this.findNavController().navigate(action)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}