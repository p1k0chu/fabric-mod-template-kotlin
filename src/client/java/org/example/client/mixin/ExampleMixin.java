package org.example.client.mixin;

import net.minecraft.client.multiplayer.ClientHandshakePacketListenerImpl;
import net.minecraft.network.protocol.login.ClientboundHelloPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientHandshakePacketListenerImpl.class)
public class ExampleMixin {
    @Inject(method = "handleHello", at = @At("HEAD"))
    void logHello(ClientboundHelloPacket packet, CallbackInfo ci) {
        System.out.printf("Got clientbound login hello packet: server_id=\"%s\", auth=%s",
                packet.getServerId(),
                packet.shouldAuthenticate());
    }
}
