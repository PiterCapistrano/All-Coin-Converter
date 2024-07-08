import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
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
fun Home() {
    var dollar by remember { mutableStateOf("") }
    var real by remember { mutableStateOf("") }

    var selectedCurrencyFrom by remember { mutableStateOf("USD") }
    var selectedCurrencyTo by remember { mutableStateOf("BRL") }

    val currencyList = listOf(
        "USD",
        "EUR",
        "BRL",
        "JPY",
        "GBP",
        "AUD",
        "CAD",
        "CHF",
        "CNY",
        "SEK",
        "BitCoin",
        "Ethereum")

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = Color.Blue,
            ) {
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
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
            }
            CurrencyInputField(
                value = dollar,
                onValueChange = {
                    dollar = it
                    if (dollar.isEmpty()) {
                        real = ""
                    } else {
                        val convertedValue = dollar.toDouble() * 5.68 // Lógica de conversão temporária
                        real = convertedValue.toString()
                    }
                },
                label = "Amount",
                selectedCurrency = selectedCurrencyFrom,
                onCurrencyChange = { selectedCurrencyFrom = it },
                currencyList = currencyList
            )

            CurrencyInputField(
                value = real,
                onValueChange = {
                    real = it
                    if (real.isEmpty()) {
                        dollar = ""
                    } else {
                        val convertedValue = real.toDouble() / 5.68 // Lógica de conversão temporária
                        dollar = convertedValue.toString()
                    }
                },
                label = "Converted Amount",
                selectedCurrency = selectedCurrencyTo,
                onCurrencyChange = { selectedCurrencyTo = it },
                currencyList = currencyList
            )
        }
    }
}

@Composable
fun CurrencyInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    selectedCurrency: String,
    onCurrencyChange: (String) -> Unit,
    currencyList: List<String>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.DarkGray,
                cursorColor = Color.Blue,
                backgroundColor = Color.White,
                textColor = Color.Black,
                focusedLabelColor = Color.DarkGray
            ),
            modifier = Modifier.weight(1f),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            maxLines = 1,
            textStyle = TextStyle(
                fontSize = 18.sp
            )
        )

        Spacer(modifier = Modifier.width(8.dp))

        CurrencyDropdownMenu(
            selectedCurrency = selectedCurrency,
            onCurrencyChange = onCurrencyChange,
            currencyList = currencyList
        )
    }
}

@Composable
fun CurrencyDropdownMenu(
    selectedCurrency: String,
    onCurrencyChange: (String) -> Unit,
    currencyList: List<String>
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedTextField(
            value = selectedCurrency,
            onValueChange = { },
            readOnly = true,
            label = { Text("Currency") },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = null,
                    Modifier.clickable { expanded = true }
                )
            },
            modifier = Modifier.width(100.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.DarkGray,
                cursorColor = Color.Blue,
                backgroundColor = Color.White,
                textColor = Color.Black,
                focusedLabelColor = Color.DarkGray
            )
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            currencyList.forEach { currency ->
                DropdownMenuItem(onClick = {
                    onCurrencyChange(currency)
                    expanded = false
                }) {
                    Text(text = currency)
                }
            }
        }
    }
}
