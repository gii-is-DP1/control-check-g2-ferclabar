package org.springframework.samples.petclinic.feeding;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FeedingService {

    private FeedingRepository feedingRepository;

    @Autowired
    public FeedingService(FeedingRepository feedingRepository){
        this.feedingRepository = feedingRepository;
    }

    @Transactional
    public List<Feeding> getAll(){
        return this.feedingRepository.findAll();
    }

    @Transactional
    public List<FeedingType> getAllFeedingTypes(){
        return null;
    }

    @Transactional
    public FeedingType getFeedingType(String typeName) {
        return this.feedingRepository.findFeedingTypesByName(typeName);
    }

    @Transactional(rollbackOn = UnfeasibleFeedingException.class)
    public Feeding save(Feeding p) throws UnfeasibleFeedingException {
        FeedingType feedingType = p.getFeedingType();
        if(p.getPet().getType().getId() != feedingType.getPetType().getId()){
            throw new UnfeasibleFeedingException();
        }else{
            return this.feedingRepository.save(p);
        }          
    }

    
}
