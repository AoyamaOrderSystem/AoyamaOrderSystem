package co.jp.aoyama.macchinetta.domain.service.order;


import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.jp.aoyama.macchinetta.domain.model.Model;
import co.jp.aoyama.macchinetta.domain.repository.order.ModelRepository;

@Service
@Transactional
public class ModelServiceImpl implements ModelService{
	@Inject
	ModelRepository modelRepository;

	@Override
	public List<Model> getAllModel(String type) {
		List<Model> modelList = modelRepository.getAllModel(type);
		return modelList;
	}

}
