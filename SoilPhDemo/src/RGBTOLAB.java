
public class RGBTOLAB {
	public void rgb2lab(int sR, int sG, int sB, int[] lab) {
		//http://www.brucelindbloom.com

		float r, g, b, X, Y, Z;
		float Ls, as, bs;

		float Xr = 95.047f;  // reference white D50
		float Yr = 100f;
		float Zr = 108.883f;

		// RGB to XYZ
		r = sR/255.f; //R 0..1
		g = sG/255.f; //G 0..1
		b = sB/255.f; //B 0..1

		// assuming sRGB (D65)
		if (r <= 0.04045)
			r = r/12.92f;
		else
			r = (float) Math.pow((r+0.055)/1.055,2.4);

		if (g <= 0.04045)
			g = g/12.92f;
		else
			g = (float) Math.pow((g+0.055)/1.055,2.4);

		if (b <= 0.04045)
			b = b/12.92f;
		else
			b = (float) Math.pow((b+0.055)/1.055,2.4);

		r=r*100;
		g=g*100;
		b=b*100;

		X =  0.4124f*r     + 0.3576f*g + 0.1805f *b;
		Y =  0.2126f*r     + 0.7152f *g + 0.0755f *b;
		Z =  0.0193f*r     + 0.1192f*g + 0.9505f  *b;

		// XYZ to Lab
		X= X/Xr;
		Y= Y/Yr;
		Z= Z/Zr;

		if ( X > 0.008856 )
			X =  (float) Math.pow(X, 1/3.);
		else
			X = (float) ((7.787f*X)+(16/116));

		if ( Y > 0.008856 )
			Y =  (float) Math.pow(Y, 1/3.);
		else
			Y= (float) ((7.787f*Y)+(16/116));

		if ( Z > 0.008856 )
			Z =  (float) Math.pow(Z, 1/3.);
		else
			Z = (float) ((7.787f*Z)+(16/116));

		Ls = ( 116 * Y ) - 16;
		as = 500*(X-Y);
		bs = 200*(Y-Z);

		//eliminating light 
		//lab[0] =(int) Ls;
		lab[0] = (int)as; 
		lab[1] =(int) bs;  
	} 
}
