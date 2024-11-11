package com.example.tictactoe

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.ui.theme.back
import com.example.tictactoe.ui.theme.jigar

@Composable
fun ScreenTwo() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
                Text(
                    text = "Tic Tac Toe",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .clip(RoundedCornerShape(bottomEnd = 15.dp, bottomStart = 15.dp))
                        .background(jigar)
                        .padding(vertical = 20.dp, horizontal = 30.dp)

                )
            }
            Spacer(modifier = Modifier.height(50.dp))
            Text(text = "Player 's Turn",
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 35.sp,
                textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(20.dp))
            TicTacToeGame()
        }
    }
}
@Composable
fun TicTacToeGrid() {
    // Har bir katak uchun belgi holati ("X", "O" yoki "")
    var boardState = remember { mutableStateListOf("", "", "", "", "", "", "", "", "") }
    var currentPlayer by remember { mutableStateOf("X") }
    val backgroundColorState = remember { mutableStateListOf(Color.LightGray, Color.LightGray, Color.LightGray, Color.LightGray, Color.LightGray, Color.LightGray, Color.LightGray, Color.LightGray, Color.LightGray) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentPadding = PaddingValues(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(9) { index ->
                    Box(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .background(
                                backgroundColorState[index],
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable {
                                if (boardState[index] == "") {  // Agar katak bo'sh bo'lsa
                                    boardState[index] =
                                        currentPlayer // Hozirgi o'yinchining belgisi qo'shiladi
                                    backgroundColorState[index] =
                                        if (currentPlayer == "X") Color(0xFFFFEB3B) else Color(
                                            0xFFFFCDD2
                                        ) // Rangni o'zgartirish
                                    currentPlayer =
                                        if (currentPlayer == "X") "O" else "X" // Navbatni o'zgartirish
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = boardState[index], // Katakdagi belgini ko'rsatish
                            color = if (boardState[index] == "X") Color(0xFF5D4037) else Color.Red,
                            fontWeight = FontWeight.Bold,
                            fontSize = 50.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Pastki qismdagi belgilar (faqat dizayn uchun, bosilganda hech nima bo'lmaydi)
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color(0xFFFFEB3B), shape = RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "X",
                        color = Color(0xFF5D4037),
                        fontWeight = FontWeight.Bold,
                        fontSize = 50.sp,
                        textAlign = TextAlign.Center
                    )
                }

                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color(0xFFFFCDD2), shape = RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "O",
                        color = Color.Red,
                        fontWeight = FontWeight.Bold,
                        fontSize = 50.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
@Composable
fun TicTacToeGame() {
    // O'yin taxtasi
    val board = remember { mutableStateListOf("", "", "", "", "", "", "", "", "") }
    var currentPlayer by remember { mutableStateOf(getRandomPlayer()) } // Tasodifiy boshlash
    var winner by remember { mutableStateOf<String?>(null) }
    val backgroundColorState = remember { mutableStateListOf(Color.LightGray, Color.LightGray, Color.LightGray, Color.LightGray, Color.LightGray, Color.LightGray, Color.LightGray, Color.LightGray, Color.LightGray) }

    // Agar kompyuter boshlashini xohlasangiz, unda LaunchedEffect bilan kompyuterning birinchi harakatini qo'shamiz.
    LaunchedEffect(currentPlayer) {
        if (currentPlayer == "O") {
            computerMove(board) // Agar kompyuter boshlasa, u birinchi harakatini amalga oshiradi
            winner = checkWinner(board)
            currentPlayer = "X" // Keyingi harakat foydalanuvchi tomonidan bo'ladi
        }
    }

    // UI
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Tic Tac Toe",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.size(300.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(9) { index ->
                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                        .clickable(enabled = board[index].isEmpty() && winner == null) {
                            if (currentPlayer == "X" && board[index].isEmpty()) {
                                board[index] = currentPlayer
                                winner = checkWinner(board)
                                if (winner == null) {
                                    currentPlayer = "O"
                                    computerMove(board) // Kompyuter harakatini amalga oshirish
                                    winner = checkWinner(board)
                                    currentPlayer = "X" // Foydalanuvchi o'z harakatini amalga oshiradi
                                }
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = board[index],
                        fontSize = 50.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (board[index] == "X") Color.Blue else Color.Red,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = when (winner) {
                "X" -> "Player X Wins!"
                "O" -> "Computer Wins!"
                else -> if (board.all { it.isNotEmpty() }) "It's a Draw!" else "Current Player: $currentPlayer"
            },
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.DarkGray,
            modifier = Modifier.padding(16.dp)
        )

        Button(onClick = {
            board.fill("")
            winner = null
            currentPlayer = getRandomPlayer() // Yangi o'yinni tasodifiy boshlash
        }) {
            Text("Restart")
        }
    }
}

// Funksiya tasodifiy o'yinchi tanlash uchun
fun getRandomPlayer(): String {
    return if (Math.random() < 0.5) "O" else "X" // Agar 0 bo'lsa, kompyuter boshlaydi (O)
}

fun checkWinner(board: List<String>): String? {
    val winningPositions = listOf(
        listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8), // Rows
        listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8), // Columns
        listOf(0, 4, 8), listOf(2, 4, 6) // Diagonals
    )

    for (positions in winningPositions) {
        val (a, b, c) = positions
        if (board[a].isNotEmpty() && board[a] == board[b] && board[a] == board[c]) {
            return board[a]
        }
    }
    return null
}

fun computerMove(board: MutableList<String>) {
    // 1. Yutish imkoniyatini tekshirish
    if (tryToWinOrBlock(board, "O")) return

    // 2. Foydalanuvchini bloklash
    if (tryToWinOrBlock(board, "X")) return

    // 3. Burchaklarni egallash
    val corners = listOf(0, 2, 6, 8)
    corners.firstOrNull { board[it].isEmpty() }?.let {
        board[it] = "O"
        return
    }

    // 4. Markaziy katak
    if (board[4].isEmpty()) {
        board[4] = "O"
        return
    }

    // 5. Tasodifiy harakat
    val emptyCells = board.indices.filter { board[it].isEmpty() }
    if (emptyCells.isNotEmpty()) {
        val randomCell = emptyCells.random()
        board[randomCell] = "O"
    }
}

fun tryToWinOrBlock(board: MutableList<String>, symbol: String): Boolean {
    val winningPositions = listOf(
        listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8), // Rows
        listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8), // Columns
        listOf(0, 4, 8), listOf(2, 4, 6) // Diagonals
    )

    // Try to win or block the opponent
    for (positions in winningPositions) {
        val (a, b, c) = positions
        if (board[a] == symbol && board[b] == symbol && board[c].isEmpty()) {
            board[c] = "O" // Kompyuterning belgisi joylashtiriladi
            return true
        }
        if (board[a] == symbol && board[c] == symbol && board[b].isEmpty()) {
            board[b] = "O"
            return true
        }
        if (board[b] == symbol && board[c] == symbol && board[a].isEmpty()) {
            board[a] = "O"
            return true
        }
    }
    return false
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShowScreen2(){
    ScreenTwo()
}