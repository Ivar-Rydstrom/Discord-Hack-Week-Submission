package hackweek.group.filterbot

import com.google.auth.oauth2.GoogleCredentials
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter

class MessageListener(gcpAuth: GoogleCredentials, gcpProjectID: String) : ListenerAdapter() {

    var database = Database(gcpAuth, gcpProjectID)
    var commandManager = CommandManager(database)

    override fun onMessageReceived(event: MessageReceivedEvent?) {
        super.onMessageReceived(event)

        if (event == null) return

        if (event.message.isCommand(event.guild.id)) {
            commandManager.handle(event)
        } else {

            if (event.message.hasImage()) {
                // TODO
            }

            if (event.message.hasImage()) {
                // TODO
            }

            if (event.message.hasText()) {
                // TODO
            }
        }

    }

    private fun Message.isCommand(guildID: String): Boolean =
        this.contentStripped.toLowerCase()
            .startsWith(database.getCommandPrefix(guildID))
}


fun Message.hasImage(): Boolean {
    this.attachments.forEach {
        if (it.isImage) return true
    }

    if (this.contentStripped.isNullOrEmpty()) return false
    val text = this.contentStripped.toLowerCase()
    return text.contains(".png") ||
            text.contains(".jpg") ||
            text.contains(".jpeg") ||
            text.contains(".tiff") ||
            text.contains(".bmp")

}

fun Message.hasVideo(): Boolean {
    // TODO
    return false
}

fun Message.hasText(): Boolean {
    // TODO
    return false
}