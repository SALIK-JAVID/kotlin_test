import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MathGameApp()
        }
    }
}

@Composable
fun MathGameApp() {
    var num1 by remember { mutableStateOf(Random.nextInt(1, 11)) }
    var num2 by remember { mutableStateOf(Random.nextInt(1, 11)) }
    var answer by remember { mutableStateOf(TextFieldValue("")) }
    var turnsLeft by remember { mutableStateOf(3) }
    var message by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.Center) {
        Text(text = "Solve: $num1 + $num2", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(value = answer, onValueChange = { answer = it })
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val userAnswer = answer.text.toIntOrNull()
            if (userAnswer == num1 + num2) {
                message = "Correct!"
            } else {
                message = "Wrong! Try again."
                turnsLeft--
            }
            if (turnsLeft > 0) {
                num1 = Random.nextInt(1, 11)
                num2 = Random.nextInt(1, 11)
                answer = TextFieldValue("")
            } else {
                message = "Game Over!"
            }
        }) {
            Text(text = "Submit")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Turns left: $turnsLeft")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = message)
        if (turnsLeft == 0) {
            Button(onClick = {
                turnsLeft = 3
                message = ""
                num1 = Random.nextInt(1, 11)
                num2 = Random.nextInt(1, 11)
                answer = TextFieldValue("")
            }) {
                Text(text = "Restart Game")
            }
        }
    }
}
