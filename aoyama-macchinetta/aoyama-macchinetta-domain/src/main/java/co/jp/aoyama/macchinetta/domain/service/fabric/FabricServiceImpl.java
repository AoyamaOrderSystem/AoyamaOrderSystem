package co.jp.aoyama.macchinetta.domain.service.fabric;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.jp.aoyama.macchinetta.domain.model.Fabric;
import co.jp.aoyama.macchinetta.domain.repository.fabric.FabricRepository;
import co.jp.aoyama.macchinetta.domain.service.fabric.FabricService;

/**
 * 生地情報
 * @author wuzhengyang
 *
 */
@Service
@Transactional
public class FabricServiceImpl  implements FabricService {
	
	@Inject
	FabricRepository fabricRepository;

	/**
	 * すべての生地を調べる
	 */
	@Override
	public List<Fabric> fabricQueryAll() {
		List<Fabric> fabricList = fabricRepository.fabricQueryAll();
		return fabricList;
	}

	/**
	 * 生地を更新する
	 */
	@Override
	public String[] fabricUpdate(List<Fabric> fabricList) {
		//新規オブジェクトのリストを格納する
		List<Fabric> fabricInsertList = new ArrayList<Fabric>();
		//修正オブジェクトのリストを格納する
		List<Fabric> fabricUpdateList = new ArrayList<Fabric>();
		//削除オブジェクトのリストを格納する
		List<Fabric> fabricDeleteList = new ArrayList<Fabric>();
		
		//新規、修正、削除数を格納し、関数の戻り値はこの配列である
		String[] countStringArr = {"0","0","0"};
		//新規本数
		Integer insertCount = 0;
		//修正本数
		Integer updateCount = 0;
		//削除本数
		Integer deleteCount = 0;
		
		for (Fabric fabric : fabricList) {
			if("I".equals(fabric.getHandleDiscriminate())) {
				//処理区分をIとし、新規に追加する
				//作成時間を現在の時間に設定する
				fabric.setCreatedAt(new Date());
				//修正時間を現在の時間に設定する
				fabric.setUpdatedAt(new Date());
				//新規本数＋１
				insertCount++;
				//新規オブジェクトをリストに入れる
				fabricInsertList.add(fabric);
			}else if("U".equals(fabric.getHandleDiscriminate())) {
				//処理区分をUとし、修正に追加する
				//修正時間を現在の時間に設定する
				fabric.setUpdatedAt(new Date());
				//修正本数＋１
				updateCount++;
				//修正オブジェクトをリストに入れる
				fabricUpdateList.add(fabric);
			}else if("D".equals(fabric.getHandleDiscriminate())) {
				//処理区分をDとし、削除に追加する
				//削除本数＋１
				deleteCount++;
				//削除オブジェクトをリストに入れる
				fabricDeleteList.add(fabric);
			}
		}
		
		if(fabricInsertList.size() != 0) {
			//新規リストの長さが０でない場合
			//データベースのfabric表にデータを追加する
			fabricRepository.fabricInsertByPrimaryKey(fabricInsertList);
			//データベースのstock表にデータを追加する
			fabricRepository.stockInsertByPrimaryKey(fabricInsertList);
		}
		
		if(fabricUpdateList.size() != 0) {
			//修正リストの長さが０でない場合
			//データベースのfabric表にデータを修正する
			fabricRepository.fabricUpdateByPrimaryKey(fabricUpdateList);
			//データベースのstock表にデータを修正する
			fabricRepository.stockUpdateByPrimaryKey(fabricUpdateList);
		}
		
		if(fabricDeleteList.size() != 0) {
			//削除リストの長さが０でない場合
			//データベースのfabric表にデータを削除する
			fabricRepository.fabricDeleteByPrimaryKey(fabricDeleteList);
			//データベースのstock表にデータを削除する
			fabricRepository.stockDeleteByPrimaryKey(fabricDeleteList);
		}
		
		//新規、修正、削除数された本数は、それぞれ配列に配置されます
		countStringArr[0] = String.valueOf(insertCount);
		countStringArr[1] = String.valueOf(updateCount);
		countStringArr[2] = String.valueOf(deleteCount);

		return countStringArr;
	}
	
	/**
	 * 主キーから生地情報が存在するかどうかを調べる
	 */
	@Override
	public boolean fabricCheckExistence(String orderPattern, String fabricId) {
		Fabric fabricQueryByPrimaryKey = fabricRepository.fabricQueryByPrimaryKey(orderPattern, fabricId);
		if(fabricQueryByPrimaryKey == null) {
			//生地存在しない
			return false;
		}else {
			//生地存在
			return true;
		}
	}
	
	/**
	 * Fabric brandが存在するかどうか
	 */
	@Override
	public boolean fablicBrandNoCheckExistence(String fablicBrandNo) {
		Fabric fablicBrandNoQuery = fabricRepository.fablicBrandNoQuery(fablicBrandNo);
		if(fablicBrandNoQuery == null) {
			//Fabric brand存在しない
			return false;
		}else {
			//Fabric brand存在
			return true;
		}
	}
}
