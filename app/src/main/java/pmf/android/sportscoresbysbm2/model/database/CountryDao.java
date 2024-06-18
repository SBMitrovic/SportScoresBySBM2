package pmf.android.sportscoresbysbm2.model.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import pmf.android.sportscoresbysbm2.model.Country;


@Dao
public interface CountryDao {

    @Query("SELECT * FROM country")
    List<Country> getAll();

    @Insert
    void insert(Country country);

    @Delete
    void delete(Country country);
}
