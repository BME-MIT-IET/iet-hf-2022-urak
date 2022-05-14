package sk.kasper.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(
    entity = LaunchEntity::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("launchId"),
    onDelete = CASCADE)],
        indices = [Index("launchId", "type", unique = true)],
        tableName = "tag")
data class TagEntity(
        @PrimaryKey(autoGenerate = true) val id: Long? = null,
        val launchId: String,
        val type: Long
)