package com.haflati.model

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class CurrencyType(val titleAr: String, val symbolAr: String, val defaultRateFromSar: Double) {
    SAR("الريال السعودي", "ر.س", 1.0),
    YER("الريال اليمني", "ر.ي", 140.0),
    USD("الدولار الأمريكي", "$", 0.267)
}

enum class EventType(val titleAr: String) {
    WEDDING("زفاف"), ENGAGEMENT("خطوبة"), GRADUATION("تخرّج"), AQD_QIRAN("عقد قران"), OTHER("مناسبة أخرى")
}

enum class ServiceCategory(val titleAr: String) {
    HALLS("قاعات واستراحات"), KOSHA_FLOWERS("الكوشة والتنسيق والورد"),
    PHOTOGRAPHY("التصوير والفيديو"), HOSPITALITY("الضيافة والبوفيه"),
    ZAFFA_BANDS("الزفّات والفرق والفقرات"), CARS("تأجير السيارات"),
    SALONS_MAKEUP("المشاغل والمكياج"), INVITES_GIFTS("الدعوات والهدايا والتوزيعات"),
    LIGHTING_AUDIO("الإضاءة والصوتيات")
}

@Entity(tableName = "cart_items")
data class CartItemEntity(
    @PrimaryKey val serviceId: String,
    val serviceTitle: String,
    val categoryName: String,
    val unitPrice: Double,
    val depositPercent: Int,
    val quantity: Int,
    val selectedDate: String
)