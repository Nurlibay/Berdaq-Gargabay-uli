package uz.texnopos.berdaqgargabayuli.presenter.main.songs.song

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.texnopos.berdaqgargabayuli.R
import uz.texnopos.berdaqgargabayuli.databinding.FragmentOneSongBinding
import uz.texnopos.berdaqgargabayuli.utils.ResourceState
import uz.texnopos.berdaqgargabayuli.utils.showMessage

class SongFragment : Fragment(R.layout.fragment_one_song) {

    private lateinit var binding: FragmentOneSongBinding
    private val args: SongFragmentArgs by navArgs()
    private val viewModel: SongViewModel by viewModel()

    var mediaPlayer = MediaPlayer()
    private var totalTime: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOneSongBinding.bind(view).apply {
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }

        viewModel.getSongById(args.id)
        setupObserver()

        val songTitle = args.songName
        val songFileName = args.song

        if(args.song == ""){
            binding.audioPLayer.visibility = View.GONE
        } else {

            val description = requireActivity().assets.openFd(args.song)
            val mp = MediaPlayer()
            mp.setDataSource(
                description.fileDescriptor,
                description.startOffset,
                description.length
            )
            description.close()
            totalTime = mp.duration

            binding.playbuttonStop.setOnClickListener {
                try {
                    if (mediaPlayer.isPlaying) {
                        mediaPlayer.pause()
                    } else {
                        val descriptor = requireActivity().assets.openFd(args.song)
                        mediaPlayer.isLooping = false
                        mediaPlayer.setVolume(0.5f, 0.5f)
                        mediaPlayer.setDataSource(
                            descriptor.fileDescriptor,
                            descriptor.startOffset,
                            descriptor.length
                        )
                        descriptor.close()
                        mediaPlayer.prepare()
                        mediaPlayer.setOnPreparedListener {
                            mediaPlayer.start()

                        }
                        totalTime = mediaPlayer.duration
                        binding.progressBarSONG.max = totalTime

                    }
                } catch (ex: java.lang.Exception) {

                }
            }

            binding.progressBarVOLUME.setOnSeekBarChangeListener(
                object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(
                        seekBar: SeekBar?,
                        progress: Int,
                        fromUser: Boolean
                    ) {
                        if (fromUser) {
                            var volumeNum = progress / 100.0f
                            mediaPlayer.setVolume(volumeNum, volumeNum)
                        }
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    }

                    override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    }

                }
            )
            binding.progressBarSONG.setOnSeekBarChangeListener(
                object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(
                        seekBar: SeekBar?,
                        progress: Int,
                        fromUser: Boolean
                    ) {
                        if (fromUser) {
                            mediaPlayer.seekTo(progress)
                        }
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    }

                    override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    }

                }
            )


            var isRotating = false

            @SuppressLint("HandlerLeak")
            val handler = object : Handler() {
                override fun handleMessage(msg: Message) {
                    val currentPosition = msg.what

                    if (!mediaPlayer.isPlaying) {
                        isRotating = false

                        binding.progressBarSONG.progress = 0
                        binding.playbuttonStop.setImageResource(R.mipmap.playsong_icono_dashboard)
                        mediaPlayer.reset()

                    } else {
                        binding.progressBarSONG.progress = currentPosition
                        binding.playbuttonStop.setImageResource(R.mipmap.stopsong_icono_dashboard)
                    }
                }

            }
            //Thread
            Thread(Runnable
            {
                while (mediaPlayer != null) {
                    try {
                        var msg = Message()
                        msg.what = mediaPlayer.currentPosition
                        handler.sendMessage(msg)
                        Thread.sleep(1000)

                    } catch (e: Exception) {

                    }
                }

            }).start()
        }

    }


    override fun onPause() {
        super.onPause()
        mediaPlayer.stop()
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer.stop()
    }

    private fun setupObserver() {
        viewModel.song.observe(viewLifecycleOwner) {
            when (it.status) {
                ResourceState.LOADING -> {}
                ResourceState.SUCCESS -> {
                    binding.tvLifeInfo.text = it.data!!.song_text
                    binding.toolbar.title = it.data.song_name
                }
                ResourceState.ERROR -> {
                    showMessage(it.message)
                }
            }
        }
    }
}