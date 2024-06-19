package pmf.android.sportscoresbysbm2.model.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import pmf.android.sportscoresbysbm2.model.League;

@Dao
public interface LeagueDao {

    @Query("SELECT * FROM league")
    List<League> getAll();

    @Insert
    void insert(League league);

    @Delete
    void delete(League league);
}
