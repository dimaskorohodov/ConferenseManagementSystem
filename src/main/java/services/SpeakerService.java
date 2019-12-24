package services;

import model.Speaker;
import persiatance.dao.sql.UserSqlDao;
import util.Finals;

public class SpeakerService {


    public int getSpeakerBonus(Speaker speaker) {
        return speaker.getRating() * Finals.BonusKoeficient;
    }
}
