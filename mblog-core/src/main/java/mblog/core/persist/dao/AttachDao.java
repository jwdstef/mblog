/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package mblog.core.persist.dao;

import mblog.core.persist.entity.AttachPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Set;

/**
 * @author langhsu
 *
 */
public interface AttachDao extends JpaRepository<AttachPO, Long>, JpaSpecificationExecutor<AttachPO> {
	List<AttachPO> findAllByToId(long toId);
	List<AttachPO> findAllByToIdIn(Set<Long> toIds);
	List<AttachPO> findAllByIdIn(Set<Long> ids);
	void deleteAllByToId(long toId);
}
