package javafxapp.api.dao;

import javafxapp.api.dto.UsrResponseDto;
import javafxapp.api.dto.UsrsListResponseDto;
import org.junit.jupiter.api.Test;

public class UsrDataApiDaoTest {
    final UsrDataApiDao usrDataApiDao = new UsrDataApiDao();

    @Test
    public void getUsrById() {
        UsrResponseDto usrResponseDto = usrDataApiDao.getUsrById(8L, "");

        assert(usrResponseDto.getUser() != null);
    }

    @Test
    public void getUsrByUsrsListByName() {
    }

    @Test
    public void testGetAllUsrs() {
        final UsrsListResponseDto allUsrs = usrDataApiDao.getAllUsrs("");

        assert(allUsrs.getUsers().size() > 0);
    }

    @Test
    public void testGetUsrAvatarById() {
        final byte[] avatar = usrDataApiDao.getUsrAvatarById(8, "");

        assert(avatar != null);
    }
}
