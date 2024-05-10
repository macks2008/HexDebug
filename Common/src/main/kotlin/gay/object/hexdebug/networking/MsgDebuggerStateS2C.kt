package gay.`object`.hexdebug.networking

import dev.architectury.networking.NetworkManager.PacketContext
import gay.`object`.hexdebug.HexDebug
import gay.`object`.hexdebug.items.ItemDebugger
import net.minecraft.network.FriendlyByteBuf
import java.util.function.Supplier

data class MsgDebuggerStateS2C(private val debuggerState: ItemDebugger.DebugState) {
    constructor(buf: FriendlyByteBuf) : this(
        buf.readEnum(ItemDebugger.DebugState::class.java),
    )

    fun encode(buf: FriendlyByteBuf) {
        buf.writeEnum(debuggerState)
    }

    fun apply(supplier: Supplier<PacketContext>) = supplier.get().also { ctx ->
        ctx.queue {
            HexDebug.LOGGER.debug("Client received packet: {}", this)
            ItemDebugger.debugState = debuggerState
        }
    }
}
