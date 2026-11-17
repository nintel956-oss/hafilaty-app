fun setCurrency(currency: CurrencyType) {
    prefs.setCurrency(currency)
    _localState.update { it.copy(currency = currency) }
}

fun updateExchangeRate(currency: CurrencyType, newRate: Double) {
    if (newRate <= 0) return
    prefs.setExchangeRate(currency, newRate)
    _localState.update { state ->
        state.copy(exchangeRates = state.exchangeRates + (currency to newRate))
    }
}

fun openWhatsAppSupport(context: Context, summaryText: String) {
    val cleanPhone = uiState.value.supportPhone.replace("+", "").replace(" ", "")
    val url = "https://wa.me/$cleanPhone?text=${Uri.encode(summaryText)}"
    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}