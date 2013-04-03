package kr.co.ujin.core.mapper.normal;

import kr.co.ujin.service.main.entity.Finance;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonMapper {
	//Test
	@Insert("insert into tb_finance(seq, workdate, pay, worker) values(1, now(), 10000, 'goodwilldd')")
	public int doInsertTest();
	//Test
	@Select("select * from tb_finance where seq=#{seq}")
	public Finance getFinance(int seq);
}
