@file:JvmName("HexDebugAbstractionsImpl")

package gay.`object`.hexdebug.forge

import gay.`object`.hexdebug.registry.HexDebugRegistrar
import net.minecraftforge.registries.RegisterEvent
import thedarkcolour.kotlinforforge.forge.MOD_BUS

fun <T : Any> initRegistry(registrar: HexDebugRegistrar<T>) {
    MOD_BUS.addListener { event: RegisterEvent ->
        event.register(registrar.registryKey) { helper ->
            registrar.init(helper::register)
        }
    }
}
