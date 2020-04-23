/*ID:20132071 DATE:2019-05-11*/
package myPackage;

public class Seat
{
	private int rowNo, seatNo;

	public Seat(int rowNo, int seatNo)
	{
		this.rowNo = rowNo;
		this.seatNo = seatNo;
	}

	public int getRowNo()
	{
		return rowNo;
	}

	public void setRowNo(int rowNo)
	{
		this.rowNo = rowNo;
	}

	public int getSeatNo()
	{
		return seatNo;
	}

	public void setSeatNo(int seatNo)
	{
		this.seatNo = seatNo;
	}
}