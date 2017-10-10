package Helper;

public class Size {
	 public int width, height;
     public Size(int _w, int _h)
     {
         this.width = _w;
         this.height = _h;
     }
     
     public void setSize(Size size) {
    	 setSize(size.width, size.height);
     }
     public void setSize(int _w, int _h) {
    	 this.width = _w;
    	 this.height = _h;
     }
}
