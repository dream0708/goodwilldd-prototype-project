package kr.co.insoft.core.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;
import org.springframework.util.Assert;

/**
 * ehCache 를 이용하여 1개의 세션만을 유지
 * 
 * @author GoodwillDD (kr.goodwilldd@gmail.com)
 *
 */
public class ClusteredSessionRegistryImpl implements SessionRegistry,
		ApplicationListener<SessionDestroyedEvent> {

	private static final Log logger = LogFactory
			.getLog(ClusteredSessionRegistryImpl.class);
	private CacheManager cacheManager = CacheManager.getInstance();
	private Cache sessionIds = cacheManager.getCache("sessionIds");

	@Override
	public void onApplicationEvent(SessionDestroyedEvent event) {
		if (event instanceof HttpSessionDestroyedEvent) {
			String sessionId = ((HttpSession) event.getSource()).getId();
			removeSessionInformation(sessionId);
		}
	}

	@Override
	public List<Object> getAllPrincipals() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SessionInformation> getAllSessions(Object principal,
			boolean includeExpiredSessions) {
		Set<String> sessionsUsedByPrincipal = getSessionIds(principal);
		List<SessionInformation> list = new ArrayList<SessionInformation>();
		Iterator<String> iter = sessionsUsedByPrincipal.iterator();

		while (iter.hasNext()) {
			String sessionId = iter.next();
			SessionInformation sessionInformation = getSessionInformation(sessionId);

			if (includeExpiredSessions || !sessionInformation.isExpired()) {
				list.add(sessionInformation);
			}
		}
		return null;
	}

	private Set<String> getSessionIds(Object principal) {
		Map<Object, Element> collection = sessionIds.getAll(sessionIds
				.getKeys());
		logger.info("Map size " + collection.size());
		Set<String> principals = new HashSet<String>();

		for (Map.Entry<Object, Element> entry : collection.entrySet()) {
			Element element = entry.getValue();
			if (element != null) {
				SessionInformation sessionInformation = (SessionInformation) element
						.getObjectValue();
				if (sessionInformation.getPrincipal().equals(principal))
					principals.add(sessionInformation.getSessionId());
			} else
				continue;
		}

		return principals;
	}

	@Override
	public SessionInformation getSessionInformation(String sessionId) {
		Assert.hasText(sessionId,
				"SessionId required as per interface contract");
		Element element = sessionIds.get(sessionId);
		if (element == null) {
			logger.info("Session was null");
			return null;
		} else {
			SessionInformation session = (SessionInformation) element
					.getObjectValue();
			logger.info("getSessionInformation: Returning session: "
					+ session.getSessionId() + " principal  "
					+ session.getPrincipal() + " session expired "
					+ session.isExpired());
			return session;
		}
	}

	@Override
	public void refreshLastRequest(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerNewSession(String sessionId, Object principal) {
		Assert.hasText(sessionId,
				"SessionId required as per interface contract");
		Assert.notNull(principal,
				"Principal required as per interface contract");
		logger.info("registerNewSession: registering session " + sessionId
				+ " principal " + principal);

		List<SessionInformation> allSessionsForAPrincipal = getAllSessions(
				principal, true);
		for (SessionInformation session : allSessionsForAPrincipal) {
			logger.info("registerNewSession: Cleanup old sessions before registering");
			removeSessionInformation(session.getSessionId());
		}

		if (getSessionInformation(sessionId) != null) {
			logger.info("registerNewSession: Calling remove session");
			removeSessionInformation(sessionId);
		}

		Element element = new Element(sessionId, new SessionInformation(
				principal, sessionId, new Date()));
		sessionIds.put(element);
	}

	@Override
	public void removeSessionInformation(String sessionId) {
		Assert.hasText(sessionId,
				"SessionId required as per interface contract");
		logger.info("About to remove session " + sessionId);
		SessionInformation info = getSessionInformation(sessionId);
		if (info != null) {
			logger.info("Removing session " + sessionId + " from session cache");
			info.expireNow();
			sessionIds.replace(new Element(sessionId, info));
		}
	}

}
