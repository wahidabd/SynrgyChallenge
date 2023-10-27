package com.wahidabd.synrgy.presentation.comment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.wahidabd.synrgy.R
import com.wahidabd.synrgy.databinding.FragmentCommentBottomSheetBinding
import com.wahidabd.synrgy.domain.comment.model.Comment
import com.wahidabd.synrgy.utils.SharedPreferences
import com.wahidabd.synrgy.utils.currentMillis
import org.koin.android.ext.android.inject

class CommentBottomSheetFragment : BottomSheetDialogFragment() {

    private val viewModel: CommentViewModel by inject()
    private val pref: SharedPreferences by inject()

    private val commentAdapter by lazy { CommentAdapter() }

    private lateinit var binding: FragmentCommentBottomSheetBinding

    private var movieId = 0L

    companion object {
        fun newInstance(movieId: Long): CommentBottomSheetFragment =
            CommentBottomSheetFragment().apply {
                this.movieId = movieId
            }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCommentBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initListener()
        initObserver()
    }

    private fun initView() = with(binding) {
        rvComments.adapter = commentAdapter
    }

    private fun initListener() = with(binding) {
        imgClose.setOnClickListener { dismiss() }
        imgSend.setOnClickListener { handleSendComment() }
        commentAdapter.setOnDeleteListener {
            viewModel.delete(it)
            viewModel.getComments(movieId)
        }
    }

    private fun initObserver() {
        viewModel.getComments(movieId)

        viewModel.comments.observe(viewLifecycleOwner) {
            commentAdapter.setList(it)
        }
    }

    private fun handleSendComment() {
        val body = binding.edtComment.text.toString().trim()
        if (body.isBlank()) return

        val data = Comment(
            movieId = movieId,
            name = pref.getUser().name,
            body = body,
            createdAt = currentMillis()
        )

        viewModel.create(data)
        viewModel.getComments(movieId)
        binding.edtComment.text?.clear()
    }

    override fun getTheme(): Int {
        return R.style.BottomDialogTheme
    }

}