package net.minecraft.network.v2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.utils.ItemStack;

public class Packet5PlayerInventory extends Packet {
	public int inventoryType;
	public ItemStack[] inventory;

	public Packet5PlayerInventory() {
	}

	public void readPacketData(DataInputStream var1) throws IOException {
		this.inventoryType = var1.readInt();
		short var2 = var1.readShort();
		this.inventory = new ItemStack[var2];

		for(int var3 = 0; var3 < var2; ++var3) {
			short var4 = var1.readShort();
			if(var4 >= 0) {
				byte var5 = var1.readByte();
				short var6 = var1.readShort();
				this.inventory[var3] = new ItemStack(var4, var5, var6);
			}
		}

	}

	public void writePacket(DataOutputStream var1) throws IOException {
		var1.writeInt(this.inventoryType);
		var1.writeShort(this.inventory.length);

		for(int var2 = 0; var2 < this.inventory.length; ++var2) {
			if(this.inventory[var2] == null) {
				var1.writeShort(-1);
			} else {
				var1.writeShort((short)this.inventory[var2].itemID);
				var1.writeByte((byte)this.inventory[var2].stackSize);
				var1.writeShort((short)this.inventory[var2].itemDamage);
			}
		}

	}

	public int getPacketSize() {
		return 6 + this.inventory.length * 5;
	}
}
