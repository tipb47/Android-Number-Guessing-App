package com.example.midterm

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class ConfirmDialogFragment (val scoreId: Long, val clickListener: (scoreId: Long) -> Unit) : DialogFragment() {

    interface MyClickListener {
        fun yesPressed()
    }

    private var listener: MyClickListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = //pop up
        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.delete_confirmation))
            .setPositiveButton(getString(R.string.yes)) { _, _ -> clickListener(scoreId) }
            .setNegativeButton(getString(R.string.no)) { _, _ -> }
            .create()

    companion object {
        const val TAG = "ConfirmDialogFragment"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as MyClickListener
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}