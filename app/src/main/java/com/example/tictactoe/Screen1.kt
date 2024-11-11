package com.example.tictactoe

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.ui.theme.back
import com.example.tictactoe.ui.theme.jigar

@Composable
fun ScreenOne(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(back)){
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
            Image(painter = painterResource(id = R.drawable.tictactoe), contentDescription ="", modifier = Modifier
                .fillMaxWidth()
                .height(360.dp))
            Spacer(modifier = Modifier.height(20.dp))

        }
        Column(modifier = Modifier.align(Alignment.BottomCenter)) {
            Text(text = "Who is playing first?",
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(20.dp))

            Row {
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .height(80.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .weight(5f)
                        .background(Color.White)
                        .padding(25.dp)
                        .align(Alignment.CenterVertically),
                    contentAlignment = Alignment.Center

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.tic),
                        contentDescription = ""
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .height(80.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .weight(5f)
                        .background(Color.White)
                        .padding(23.dp)
                        .align(Alignment.CenterVertically),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.tac),
                        contentDescription = ""
                    )
                }
                Spacer(modifier = Modifier.weight(1f))

            }
            Spacer(modifier = Modifier.height(20.dp))

        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SowScreenOne(){
    ScreenOne()
}