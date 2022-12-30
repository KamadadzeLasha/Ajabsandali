package fop.w7color;

public class RgbColor {
    private final int Red;
    private final int Green;
    private final int Blue;
    private final int bitDepth;


    public int getRed() {
        return Red;
    }

    public int getGreen() {
        return Green;
    }

    public int getBlue() {
        return Blue;
    }

    public int getBitDepth() {
        return bitDepth;
    }

    public RgbColor(int bitDepth, int red, int green, int blue) {
        this.Blue = blue;
        if (blue > IntMath.powerOfTwo(bitDepth) - 1 || blue < 0) {
            ExceptionUtil.unsupportedOperation("Blue should be in bounds:[0 : " + (IntMath.powerOfTwo(bitDepth) - 1) + ']');
        }
        this.Red = red;
        if (red > IntMath.powerOfTwo(bitDepth) - 1 || red < 0) {
            ExceptionUtil.unsupportedOperation("Red should be in bounds:[0 : " + (IntMath.powerOfTwo(bitDepth) - 1) + ']');
        }
        this.Green = green;
        if (green > IntMath.powerOfTwo(bitDepth) - 1 || green < 0) {
            ExceptionUtil.unsupportedOperation("Green should be in bounds:[0 : " + (IntMath.powerOfTwo(bitDepth) - 1) + ']');
        }
        this.bitDepth = bitDepth;
    }
    private int decimalToBinaryToDecimal(int num){
        if(num == 0 ){
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        int depth = this.bitDepth;
        int binary = 0;
        int cnt = 0;
        while (num != 0) {
            int rem = num % 2;
            double c = Math.pow(10, cnt);
            binary += rem * c;
            num /= 2;
            cnt++;
        }
        String str1 = Integer.toString(binary);
        for (int i = 0; i < depth; i++) {
            if (sb.length() != depth) {
                if (str1.length() + i != depth) {
                    sb.append("0");
                }
                else {
                    sb.append(str1);
                }
            }
        }
        sb.append(String.valueOf(sb).repeat(4));
        String first8 = String.valueOf(sb).substring(0, 8);
        Integer decimal = Integer.parseInt(first8, 2);
        if (decimal > 255){
            decimal = 255;
        }
        return decimal;
    }
    public RgbColor8Bit toRgbColor8Bit() {
        if (bitDepth == 8) {
            return new RgbColor8Bit(this.Red, this.Green, this.Blue);
        }
        else if (bitDepth > 8) {
            int divisor = IntMath.powerOfTwo(bitDepth - 9);
            int max_value = IntMath.powerOfTwo(8) - 1;
            int red = this.Red / divisor;
            red /= 2;
            if (red % 2 == 1) red++;
            if (red > max_value) red = max_value;

            int green = this.Green / divisor;
            green /= 2;
            if (green % 2 == 1) green++;
            if (green > max_value) green = max_value;

            int blue = this.Blue / divisor;
            blue /= 2;
            if (blue % 2 == 1) blue++;
            if (blue > max_value) blue = max_value;
            return new RgbColor8Bit(red, green, blue);
        }
        else {
            int red = decimalToBinaryToDecimal(this.Red);
            int blue = decimalToBinaryToDecimal(this.Blue);
            int green = decimalToBinaryToDecimal(this.Green);
            return new RgbColor8Bit(red,green,blue);
        }
    }

}
