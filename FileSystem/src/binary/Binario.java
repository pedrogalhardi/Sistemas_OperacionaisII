package binary;

import java.util.*;

/**
 * Responsavel por algumas conversoes binárias
 *
 * @author douglas
 */
public class Binario {

    // Using int value 0-15 as index, yields equivalent hex digit as char.
    private static char[] chars
            = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    // Use this to produce String equivalent of unsigned int value (add it to int value, result is long)
    private static final long UNSIGNED_BASE = (long) 0x7FFFFFFF + (long) 0x7FFFFFFF + (long) 2; //0xFFFFFFFF+1

    /**
     * Transforma um valor inteiro em um String contendo 0's e 1's equivalente
     * ao valor inteiro em binario
     *
     * @param value O valor inteiro a ser convertido.
     * @param length Numero de posicoes para o String binario.
     * @return String contendo 0's e 1's equivalente ao valor inteiro
     *
     */
    public static String intToBinaryString(int value, int length) {
        char[] result = new char[length];
        int index = length - 1;
        for (int i = 0; i < length; i++) {
            result[index] = (bitValue(value, i) == 1) ? '1' : '0';
            index--;
        }
        return new String(result);
    }

    /**
     * Translate int value into a String consisting of '1's and '0's. Assumes
     * all 32 bits are to be translated.
     *
     * @param value The int value to convert.
     * @return String consisting of '1' and '0' characters corresponding to the
     * requested binary sequence.
     *
     */
    public static String intToBinaryString(int value) {
        return intToBinaryString(value, 32);
    }

    /**
     * Translate long value into a String consisting of '1's and '0's.
     *
     * @param value The long value to convert.
     * @param length The number of bit positions, starting at least significant,
     * to process.
     * @return String consisting of '1' and '0' characters corresponding to the
     * requested binary sequence.
     *
     */
    public static String longToBinaryString(long value, int length) {
        char[] result = new char[length];
        int index = length - 1;
        for (int i = 0; i < length; i++) {
            result[index] = (bitValue(value, i) == 1) ? '1' : '0';
            index--;
        }
        return new String(result);
    }

    /**
     * Translate long value into a String consisting of '1's and '0's. Assumes
     * all 64 bits are to be translated.
     *
     * @param value The long value to convert.
     * @return String consisting of '1' and '0' characters corresponding to the
     * requested binary sequence.
     *
     */
    public static String longToBinaryString(long value) {
        return longToBinaryString(value, 64);
    }

    /**
     * Translate String consisting of '1's and '0's into an int value having
     * that binary representation. The String is assumed to be at most 32
     * characters long. No error checking is performed. String position 0 has
     * most-significant bit, position length-1 has least-significant.
     *
     * @param value The String value to convert.
     * @return int whose binary value corresponds to decoded String.
     *
     */
    public static int binaryStringToInt(String value) {
        int result = value.charAt(0) - 48;
        for (int i = 1; i < value.length(); i++) {
            result = (result << 1) | (value.charAt(i) - 48);
        }
        return result;
    }

    /**
     * Translate String consisting of '1's and '0's into a long value having
     * that binary representation. The String is assumed to be at most 64
     * characters long. No error checking is performed. String position 0 has
     * most-significant bit, position length-1 has least-significant.
     *
     * @param value The String value to convert.
     * @return long whose binary value corresponds to decoded String.
     *
     */
    public static long binaryStringToLong(String value) {
        long result = value.charAt(0) - 48;
        for (int i = 1; i < value.length(); i++) {
            result = (result << 1) | (value.charAt(i) - 48);
        }
        return result;
    }

    /**
     * Translate String consisting of '1's and '0's into String equivalent of
     * the corresponding hexadecimal value. No length limit. String position 0
     * has most-significant bit, position length-1 has least-significant.
     *
     * @param value The String value to convert.
     * @return String containing '0', '1', ...'F' characters which form
     * hexadecimal equivalent of decoded String.
     *
     */
    public static String binaryStringToHexString(String value) {
        int digits = (value.length() + 3) / 4;
        char[] hexChars = new char[digits + 2];
        int position, result, pow, rep;
        hexChars[0] = '0';
        hexChars[1] = 'x';
        position = value.length() - 1;
        for (int digs = 0; digs < digits; digs++) {
            result = 0;
            pow = 1;
            rep = 0;
            while (rep < 4 && position >= 0) {
                if (value.charAt(position) == '1') {
                    result = result + pow;
                }
                pow *= 2;
                position--;
                rep++;
            }
            hexChars[digits - digs + 1] = chars[result];
        }
        return new String(hexChars);
    }

    /**
     * Translate String consisting of hexadecimal digits into String consisting
     * of corresponding binary digits ('1's and '0's). No length limit. String
     * position 0 will have most-significant bit, position length-1 has
     * least-significant.
     *
     * @param value String containing '0', '1', ...'f' characters which form
     * hexadecimal. Letters may be either upper or lower case. Works either with
     * or without leading "Ox".
     * @return String with equivalent value in binary.
     *
     */
    public static String hexStringToBinaryString(String value) {
        String result = "";
        // slice off leading Ox or 0X
        if (value.indexOf("0x") == 0 || value.indexOf("0X") == 0) {
            value = value.substring(2);
        }
        for (int digs = 0; digs < value.length(); digs++) {
            switch (value.charAt(digs)) {
                case '0':
                    result += "0000";
                    break;
                case '1':
                    result += "0001";
                    break;
                case '2':
                    result += "0010";
                    break;
                case '3':
                    result += "0011";
                    break;
                case '4':
                    result += "0100";
                    break;
                case '5':
                    result += "0101";
                    break;
                case '6':
                    result += "0110";
                    break;
                case '7':
                    result += "0111";
                    break;
                case '8':
                    result += "1000";
                    break;
                case '9':
                    result += "1001";
                    break;
                case 'a':
                case 'A':
                    result += "1010";
                    break;
                case 'b':
                case 'B':
                    result += "1011";
                    break;
                case 'c':
                case 'C':
                    result += "1100";
                    break;
                case 'd':
                case 'D':
                    result += "1101";
                    break;
                case 'e':
                case 'E':
                    result += "1110";
                    break;
                case 'f':
                case 'F':
                    result += "1111";
                    break;
            }
        }
        return result;
    }

    /**
     * Translate String consisting of '1's and '0's into char equivalent of the
     * corresponding hexadecimal digit. String limited to length 4. String
     * position 0 has most-significant bit, position length-1 has
     * least-significant.
     *
     * @param value The String value to convert.
     * @return char '0', '1', ...'F' which form hexadecimal equivalent of
     * decoded String. If string length > 4, returns '0'.
     *
     */
    public static char binaryStringToHexDigit(String value) {
        if (value.length() > 4) {
            return '0';
        }
        int result = 0;
        int pow = 1;
        for (int i = value.length() - 1; i >= 0; i--) {
            if (value.charAt(i) == '1') {
                result = result + pow;
            }
            pow *= 2;
        }
        return chars[result];
    }

    /**
     * Prefix a hexadecimal-indicating string "0x" to the string which is
     * returned by the method "Integer.toHexString". Prepend leading zeroes to
     * that string as necessary to make it always eight hexadecimal digits.
     *
     * @param d The int value to convert.
     * @return String containing '0', '1', ...'F' which form hexadecimal
     * equivalent of int.
     */
    public static String intToHexString(int d) {
        String leadingZero = new String("0");
        String leadingX = new String("0x");
        String t = Integer.toHexString(d);
        while (t.length() < 8) {
            t = leadingZero.concat(t);
        }

        t = leadingX.concat(t);
        return t;
    }

    /**
     * Prefix a hexadecimal-indicating string "0x" to the string equivalent to
     * the hexadecimal value in the long parameter. Prepend leading zeroes to
     * that string as necessary to make it always sixteen hexadecimal digits.
     *
     * @param value The long value to convert.
     * @return String containing '0', '1', ...'F' which form hexadecimal
     * equivalent of long.
     */
    public static String longToHexString(long value) {
        return binaryStringToHexString(longToBinaryString(value));
    }

    /**
     * Produce String equivalent of integer value interpreting it as an unsigned
     * integer. For instance, -1 (0xffffffff) produces "4294967295" instead of
     * "-1".
     *
     * @param d The int value to interpret.
     * @return String which forms unsigned 32 bit equivalent of int.
     */
    public static String unsignedIntToIntString(int d) {
        return (d >= 0) ? Integer.toString(d) : Long.toString(UNSIGNED_BASE + d);
    }

    /**
     * Attempt to validate given string whose characters represent a 32 bit
     * integer. Integer.decode() is insufficient because it will not allow
     * incorporation of hex two's complement (i.e. 0x80...0 through 0xff...f).
     * Allows optional negative (-) sign but no embedded spaces.
     *
     * @param s candidate string
     * @return returns int value represented by given string
     * @throws NumberFormatException if string cannot be translated into an int
     */
    public static int stringToInt(String s) throws NumberFormatException {
        String work = new String(s);
        int result = 0;
        // First, use Integer.decode().  This will validate most, but it flags
        // valid hex two's complement values as exceptions.  We'll catch those and
        // do our own validation.
        try {
            result = Integer.decode(s).intValue();
        } catch (NumberFormatException nfe) {
            // Multistep process toward validation of hex two's complement. 3-step test:
            //   (1) exactly 10 characters long,
            //   (2) starts with Ox or 0X,
            //   (3) last 8 characters are valid hex digits.
            work = work.toLowerCase();
            if (work.length() == 10 && work.startsWith("0x")) {
                String bitString = "";
                int index;
                // while testing characters, build bit string to set up for binaryStringToInt
                for (int i = 2; i < 10; i++) {
                    index = Arrays.binarySearch(chars, work.charAt(i));
                    if (index < 0) {
                        throw new NumberFormatException();
                    }
                    bitString = bitString + intToBinaryString(index, 4);
                }
                result = binaryStringToInt(bitString);
            } else {
                throw new NumberFormatException();
            }
        }
        return result;
    }

    /**
     * Attempt to validate given string whose characters represent a 64 bit
     * long. Long.decode() is insufficient because it will not allow
     * incorporation of hex two's complement (i.e. 0x80...0 through 0xff...f).
     * Allows optional negative (-) sign but no embedded spaces.
     *
     * @param s candidate string
     * @return returns long value represented by given string
     * @throws NumberFormatException if string cannot be translated into a long
     */
    public static long stringToLong(String s) throws NumberFormatException {
        String work = new String(s);
        long result = 0;
        // First, use Long.decode().  This will validate most, but it flags
        // valid hex two's complement values as exceptions.  We'll catch those and
        // do our own validation.
        try {
            result = Long.decode(s).longValue();
        } catch (NumberFormatException nfe) {
            // Multistep process toward validation of hex two's complement. 3-step test:
            //   (1) exactly 18 characters long,
            //   (2) starts with Ox or 0X,
            //   (3) last 16 characters are valid hex digits.
            work = work.toLowerCase();
            if (work.length() == 18 && work.startsWith("0x")) {
                String bitString = "";
                int index;
                // while testing characters, build bit string to set up for binaryStringToInt
                for (int i = 2; i < 18; i++) {
                    index = Arrays.binarySearch(chars, work.charAt(i));
                    if (index < 0) {
                        throw new NumberFormatException();
                    }
                    bitString = bitString + intToBinaryString(index, 4);
                }
                result = binaryStringToLong(bitString);
            } else {
                throw new NumberFormatException();
            }
        }
        return result;
    }

    /**
     * Returns int representing the bit values of the high order 32 bits of
     * given 64 bit long value.
     *
     * @param longValue The long value from which to extract bits.
     * @return int containing high order 32 bits of argument
   	  *
     */
    public static int highOrderLongToInt(long longValue) {
        return (int) (longValue >> 32);  // high order 32 bits
    }

    /**
     * Returns int representing the bit values of the low order 32 bits of given
     * 64 bit long value.
     *
     * @param longValue The long value from which to extract bits.
     * @return int containing low order 32 bits of argument
   	  *
     */
    public static int lowOrderLongToInt(long longValue) {
        return (int) (longValue << 32 >> 32);  // low order 32 bits
    }

    /**
     * Returns long (64 bit integer) combining the bit values of two given 32
     * bit integer values.
     *
     * @param highOrder Integer to form the high-order 32 bits of result.
     * @param lowOrder Integer to form the high-order 32 bits of result.
     * @return long containing concatenated 32 bit int values.
   	  *
     */
    public static long twoIntsToLong(int highOrder, int lowOrder) {
        return (((long) highOrder) << 32) | (((long) lowOrder) & 0xFFFFFFFFL);
    }

    /**
     * Returns the bit value of the given bit position of the given int value.
     *
     * @param value The value to read the bit from.
     * @param bit bit position in range 0 (least significant) to 31 (most)
     * @return 0 if the bit position contains 0, and 1 otherwise.
   	  *
     */
    public static int bitValue(int value, int bit) {
        return 1 & (value >> bit);
    }

    /**
     * Returns the bit value of the given bit position of the given long value.
     *
     * @param value The value to read the bit from.
     * @param bit bit position in range 0 (least significant) to 63 (most)
     * @return 0 if the bit position contains 0, and 1 otherwise.
   	  *
     */
    public static int bitValue(long value, int bit) {

        return (int) (1L & (value >> bit));
    }

    /**
     * Sets the specified bit of the specified value to 1, and returns the
     * result.
     *
     * @param value The value in which the bit is to be set.
     * @param bit bit position in range 0 (least significant) to 31 (most)
     * @return value possibly modified with given bit set to 1.
   	  *
     */
    public static int setBit(int value, int bit) {
        return value | (1 << bit);
    }

    /**
     * Sets the specified bit of the specified value to 0, and returns the
     * result.
     *
     * @param value The value in which the bit is to be set.
     * @param bit bit position in range 0 (least significant) to 31 (most)
     * @return value possibly modified with given bit set to 0.
   	  *
     */
    public static int clearBit(int value, int bit) {
        return value & ~(1 << bit);
    }

    // setByte and getByte added by DPS on 12 July 2006
    /**
     * Sets the specified byte of the specified value to the low order 8 bits of
     * specified replacement value, and returns the result.
     *
     * @param value The value in which the byte is to be set.
     * @param bite byte position in range 0 (least significant) to 3 (most)
     * @param replace value to place into that byte position - use low order 8
     * bits
     * @return value modified value.
   	  *
     */
    public static int setByte(int value, int bite, int replace) {
        return value & ~(0xFF << (bite << 3)) | ((replace & 0xFF) << (bite << 3));
    }

    /**
     * Gets the specified byte of the specified value.
     *
     * @param value The value in which the byte is to be retrieved.
     * @param bite byte position in range 0 (least significant) to 3 (most)
     * @return zero-extended byte value in low order byte.
   	  *
     */
    public static int getByte(int value, int bite) {
        return value << ((3 - bite) << 3) >>> 24;
    }

    // KENV 1/4/05
    /**
     * Parsing method to see if a string represents a hex number. As per
     * http://java.sun.com/j2se/1.4.2/docs/api/java/lang/Integer.html#decode(java.lang.String),
     * a string represents a hex number if the string is in the forms: Signopt
     * 0x HexDigits Signopt 0X HexDigits Signopt # HexDigits <---- Disallow this
     * form since # is MIPS comment
     *
     *
     * @param v String containing numeric digits (could be decimal, octal, or
     * hex)
     *
     * @return Returns <tt>true</tt> if string represents a hex number, else
     * returns <tt>false</tt>.
    *
     */
    public static boolean isHex(String v) {
        try {
            // don't care about return value, just whether it threw exception.
            // If value is EITHER a valid int OR a valid long, continue.
            try {
                Binario.stringToInt(v);
            } catch (NumberFormatException nfe) {
                try {
                    Binario.stringToLong(v);
                } catch (NumberFormatException e) {
                    return false;  // both failed; it is neither valid int nor long
                }
            }

            if ((v.charAt(0) == '-')
                    && // sign is optional but if present can only be -
                    (v.charAt(1) == '0')
                    && (Character.toUpperCase(v.charAt(1)) == 'X')) {
                return true;  // Form is Sign 0x.... and the entire string is parseable as a number
            } else if ((v.charAt(0) == '0')
                    && (Character.toUpperCase(v.charAt(1)) == 'X')) {
                return true;  // Form is 0x.... and the entire string is parseable as a number
            }
        } catch (StringIndexOutOfBoundsException e) {
            return false;
        }

        return false; // default
    }

    // KENV 1/4/05
    /**
     * Parsing method to see if a string represents an octal number. As per
     * http://java.sun.com/j2se/1.4.2/docs/api/java/lang/Integer.html#decode(java.lang.String),
     * a string represents an octal number if the string is in the forms:
     * Signopt 0 OctalDigits
     *
     * @param v String containing numeric digits (could be decimal, octal, or
     * hex)
     *
     * @return Returns <tt>true</tt> if string represents an octal number, else
     * returns <tt>false</tt>.
    *
     */
    public static boolean isOctal(String v) {
        // Don't mistake "0" or a string that starts "0x" for an octal string
        try {
            // we don't care what value Binary.stringToInt(v) returns, just whether it threw exception
            int dontCare = Binario.stringToInt(v);

            if (isHex(v)) {
                return false; // String starts with "0" but continues "0x", so not octal
            }
            if ((v.charAt(0) == '-')
                    && // sign is optional but if present can only be -
                    (v.charAt(1) == '0')
                    && (v.length() > 1)) // Has to have more digits than the leading zero
            {
                return true;  // Form is Sign 0.... and the entire string is parseable as a number
            } else if ((v.charAt(0) == '0')
                    && (v.length() > 1)) // Has to have more digits than the leading zero
            {
                return true;  // Form is 0.... and the entire string is parseable as a number
            }
        } catch (StringIndexOutOfBoundsException e) {
            return false;
        } catch (NumberFormatException e) {
            return false;
        }

        return false; // default
    }

}
