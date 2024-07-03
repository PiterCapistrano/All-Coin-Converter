import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Home(){

    var dollar by remember {
        mutableStateOf("")
    }

    var real by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = Color.Blue,
            ){
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "All Coin Converter",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    ){
        Column(
            modifier = Modifier.fillMaxSize()
        ){
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "5,68 Real brasileiro",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(20.dp, 50.dp, 0.dp, 0.dp)
                )
                Text(
                    "2 de jul., 22:54 UTC · Fontes",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(20.dp, 10.dp, 20.dp, 20.dp)
                )
            }
            OutlinedTextField(

                value = dollar,
                onValueChange = {
                    dollar = it

                    if(dollar.isEmpty()) {
                        dollar = ""
                        real = ""
                    }else {
                        val convertedValueSSD = dollar.toDouble() * 5.68
                        real = convertedValueSSD.toString()
                    }
                },
                label = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("American Dollar")
                    }
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.DarkGray,
                    cursorColor = Color.Blue,
                    backgroundColor = Color.White,
                    textColor = Color.Black,
                    focusedLabelColor = Color.DarkGray
                ),
                modifier = Modifier.fillMaxWidth().padding(20.dp, 5.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                maxLines = 1,
                textStyle = TextStyle(
                    fontSize = 18.sp
                )
            )

            OutlinedTextField(

                value = real,
                onValueChange = {
                    real= it

                    if(real.isEmpty()) {
                        dollar = ""
                        real = ""
                    }else {
                        val convertedValueReal = real.toDouble() / 5.68
                        dollar = convertedValueReal.toString()
                    }
                },
                label = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Brazilian Real")
                    }
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.DarkGray,
                    cursorColor = Color.Blue,
                    backgroundColor = Color.White,
                    textColor = Color.Black,
                    focusedLabelColor = Color.DarkGray
                ),
                modifier = Modifier.fillMaxWidth().padding(20.dp, 5.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                maxLines = 1,
                textStyle = TextStyle(
                    fontSize = 18.sp
                )
            )
        }
    }
}