public class BDEmployee extends SQLiteOpenHelper
{
	String sql_employee = "CREATE TABLE employee ("+
					"keyEmp INTEGER PRIMARY KEY, "+
					"nameEmp VARCHAR(50), "+
					"lastName VARCHAR(50), "+
					"lastNameMom VARCHAR(50), "+
					"bornDate Date, "+
					"emailEmp VARCHAR(50), "+
					"keyJob INTEGER, "+
					"FOREIGN KEY (keyJob) REFERENCES job (keyjob))";

	String sql_job = "CREATE TABLE job ("+
					"keyJob INTEGER PRIMARY KEY, "+
					"nameJob VARCHAR(50))";

	public DBEmployee(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
	{
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL(sql_job);
		db.execSQL(sql_employee);

		db.execSQL("INSERT INTO job(nameJob) VALUES ('Head of departmen't)")
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{

	}
}