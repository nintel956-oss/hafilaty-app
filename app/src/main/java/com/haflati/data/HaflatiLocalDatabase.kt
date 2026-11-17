@Dao
interface CartDao {
    @Query("SELECT * FROM cart_items")
    fun observeCart(): Flow<List<CartItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertCartItem(item: CartItemEntity)

    @Query("DELETE FROM cart_items WHERE serviceId = :serviceId")
    suspend fun deleteCartItem(serviceId: String)
}

class HaflatiPreferences(context: Context) {
    private val prefs = context.getSharedPreferences("haflati_prefs", Context.MODE_PRIVATE)
    fun getCurrency(): CurrencyType = CurrencyType.valueOf(prefs.getString("currency_code", "SAR") ?: "SAR")
    fun setCurrency(currency: CurrencyType) = prefs.edit().putString("currency_code", currency.name).apply()
    fun getExchangeRate(currency: CurrencyType): Double = prefs.getFloat("rate_${currency.name}", currency.defaultRateFromSar.toFloat()).toDouble()
    fun setExchangeRate(currency: CurrencyType, rate: Double) = prefs.edit().putFloat("rate_${currency.name}", rate.toFloat()).apply()
}