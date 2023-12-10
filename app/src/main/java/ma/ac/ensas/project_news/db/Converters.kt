package ma.ac.ensas.project_news.db

import androidx.room.TypeConverter
import ma.ac.ensas.project_news.models.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source) : String{
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source{
        return Source(name, name)
    }
}