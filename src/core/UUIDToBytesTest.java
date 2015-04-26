package core;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.UUID;

public class UUIDToBytesTest {

	public static void main(String args[]) {
		UUID id = UUID.randomUUID();

		System.out.println(id);
		
		byte[] idBytes = uuid2Bytes(id);
		System.out.println(Arrays.toString(idBytes));
		System.out.println(bytes2UUID(idBytes));
		
		String stringID = id.toString();
		System.out.println(stringID);
		System.out.println(UUID.fromString(stringID));
	}

	public static byte[] uuid2Bytes(UUID uuid) {
		byte[] bytes = new byte[16];
		ByteBuffer bb = ByteBuffer.wrap(bytes);
		bb.order(ByteOrder.LITTLE_ENDIAN);
		bb.putLong(uuid.getMostSignificantBits());
		bb.putLong(uuid.getLeastSignificantBits());
		return bytes;
	}

	public static UUID bytes2UUID(byte[] bytes) {
		ByteBuffer bb = ByteBuffer.wrap(bytes);
		bb.order(ByteOrder.LITTLE_ENDIAN);
		return new UUID(bb.getLong(), bb.getLong());
	}
}
