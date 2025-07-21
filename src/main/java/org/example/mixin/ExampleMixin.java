package org.example.mixin;

import net.minecraft.network.protocol.handshake.ClientIntentionPacket;
import net.minecraft.server.network.ServerHandshakePacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerHandshakePacketListenerImpl.class)
public class ExampleMixin {
    @Inject(method = "handleIntention", at = @At("HEAD"))
    void logHandshakePacket(ClientIntentionPacket packet, CallbackInfo ci) {
        System.out.printf("Got Handshake packet: host=%s, port=%d, protocol=%d, intention=%s\n",
                packet.hostName(),
                packet.port(),
                packet.protocolVersion(),
                packet.intention());
    }
}
