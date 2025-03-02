package ir.developer.goalorpooch_compose.ui.screen

//@Composable
//fun MusicControlButton(viewModel: MusicPlayerViewModel) {
//    val isPlaying by viewModel.isPlaying.collectAsStateWithLifecycle()
//    val playIcon = painterResource(id = R.drawable.volume_loud)
//    val pauseIcon = painterResource(R.drawable.no_volume_loud)
//
//    Button(
//        modifier = Modifier
//            .size(40.dp),
//        colors = ButtonColors(
//            containerColor = FenceGreen,
//            contentColor = Color.White,
//            disabledContainerColor = HihadaBrown,
//            disabledContentColor = HihadaBrown
//        ),
//        shape = RoundedCornerShape(sizeRound()),
//        contentPadding = PaddingValues(0.dp),
//        onClick = {
//            if (isPlaying) {
//                viewModel.stopMusic()
//            } else {
//                viewModel.playMusic()
//            }
//        }) {
//        Icon(
//            modifier = Modifier.size(20.dp),
//            painter = if (!isPlaying) pauseIcon else playIcon,
//            contentDescription = "volume_loud"
//        )
//    }
//}