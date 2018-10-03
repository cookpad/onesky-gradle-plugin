package com.cookpad.android.onesky.plugin

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat
import java.lang.reflect.Type


internal val gson = GsonBuilder()
        .registerTypeAdapter(object : TypeToken<DateTime>() {}.type, DateTimeConverter())
        .create()

internal class DateTimeConverter : JsonSerializer<DateTime>, JsonDeserializer<DateTime> {
    override fun serialize(src: DateTime, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        val formatter = ISODateTimeFormat.dateTimeNoMillis()
        return JsonPrimitive(formatter.print(src))
    }

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): DateTime {
        return DateTime(json.asString)
    }
}

