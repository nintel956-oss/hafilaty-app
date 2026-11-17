fun formatPriceInCurrency(amountSar: Double, currency: CurrencyType, rate: Double): String {
    val converted = amountSar * rate
    return if (currency == CurrencyType.USD) String.format("$%.2f", converted)
    else "${converted.toInt()} ${currency.symbolAr}"
}

@Composable
fun PersistentTotalCostBar(
    totalCost: Double,
    itemCount: Int,
    isOverBudget: Boolean,
    currency: CurrencyType = CurrencyType.SAR,
    exchangeRate: Double = 1.0,
    onOpenCart: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxWidth().clickable { onOpenCart() }, shadowElevation = 8.dp) {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("إجمالي التكلفة ($itemCount خدمة)", style = MaterialTheme.typography.bodyMedium)
                Text(formatPriceInCurrency(totalCost, currency, exchangeRate), style = MaterialTheme.typography.titleLarge, color = HaflatiPurple)
            }
            Button(onClick = onOpenCart, colors = ButtonDefaults.buttonColors(containerColor = HaflatiPurple)) {
                Text("عرض السلة")
            }
        }
    }
}