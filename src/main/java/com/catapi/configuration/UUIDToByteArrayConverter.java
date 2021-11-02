package com.catapi.configuration;

import java.nio.ByteBuffer;
import java.util.UUID;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

/**
 * @author rohit.godara
 *
 */
@WritingConverter
public class UUIDToByteArrayConverter implements Converter<UUID, byte[]> {
	@Override
	public byte[] convert(UUID source) {
		ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
		bb.putLong(source.getMostSignificantBits());
		bb.putLong(source.getLeastSignificantBits());
		return bb.array();
	}
}
