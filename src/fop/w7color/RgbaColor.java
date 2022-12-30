package fop.w7color;

public class RgbaColor extends RgbColor {
    private final int Alpha;

    public int getAlpha() {
        return Alpha;
    }

    public RgbaColor(int bitDepth, int red, int green, int blue, int alpha) {
        super(bitDepth, red, green, blue);
        this.Alpha = alpha;
        if (alpha > IntMath.powerOfTwo(bitDepth) - 1 || alpha < 0) {
            ExceptionUtil.unsupportedOperation("Alpha should be in bounds:[0 : " + (IntMath.powerOfTwo(bitDepth) - 1) + ']');
        }
    }

    @Override
    public RgbColor8Bit toRgbColor8Bit() {
        if (Alpha != IntMath.powerOfTwo(getBitDepth()) - 1) {
            ExceptionUtil.unsupportedOperation("Unable to convert.");
        }
        return super.toRgbColor8Bit();
    }

}
