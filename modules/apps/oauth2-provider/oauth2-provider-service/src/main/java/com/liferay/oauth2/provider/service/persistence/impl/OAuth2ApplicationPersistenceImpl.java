/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.oauth2.provider.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.oauth2.provider.exception.NoSuchOAuth2ApplicationException;
import com.liferay.oauth2.provider.model.OAuth2Application;
import com.liferay.oauth2.provider.model.impl.OAuth2ApplicationImpl;
import com.liferay.oauth2.provider.model.impl.OAuth2ApplicationModelImpl;
import com.liferay.oauth2.provider.service.persistence.OAuth2ApplicationPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the o auth2 application service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OAuth2ApplicationPersistence
 * @see com.liferay.oauth2.provider.service.persistence.OAuth2ApplicationUtil
 * @generated
 */
@ProviderType
public class OAuth2ApplicationPersistenceImpl extends BasePersistenceImpl<OAuth2Application>
	implements OAuth2ApplicationPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link OAuth2ApplicationUtil} to access the o auth2 application persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = OAuth2ApplicationImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(OAuth2ApplicationModelImpl.ENTITY_CACHE_ENABLED,
			OAuth2ApplicationModelImpl.FINDER_CACHE_ENABLED,
			OAuth2ApplicationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(OAuth2ApplicationModelImpl.ENTITY_CACHE_ENABLED,
			OAuth2ApplicationModelImpl.FINDER_CACHE_ENABLED,
			OAuth2ApplicationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OAuth2ApplicationModelImpl.ENTITY_CACHE_ENABLED,
			OAuth2ApplicationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public OAuth2ApplicationPersistenceImpl() {
		setModelClass(OAuth2Application.class);
	}

	/**
	 * Caches the o auth2 application in the entity cache if it is enabled.
	 *
	 * @param oAuth2Application the o auth2 application
	 */
	@Override
	public void cacheResult(OAuth2Application oAuth2Application) {
		entityCache.putResult(OAuth2ApplicationModelImpl.ENTITY_CACHE_ENABLED,
			OAuth2ApplicationImpl.class, oAuth2Application.getPrimaryKey(),
			oAuth2Application);

		oAuth2Application.resetOriginalValues();
	}

	/**
	 * Caches the o auth2 applications in the entity cache if it is enabled.
	 *
	 * @param oAuth2Applications the o auth2 applications
	 */
	@Override
	public void cacheResult(List<OAuth2Application> oAuth2Applications) {
		for (OAuth2Application oAuth2Application : oAuth2Applications) {
			if (entityCache.getResult(
						OAuth2ApplicationModelImpl.ENTITY_CACHE_ENABLED,
						OAuth2ApplicationImpl.class,
						oAuth2Application.getPrimaryKey()) == null) {
				cacheResult(oAuth2Application);
			}
			else {
				oAuth2Application.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all o auth2 applications.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(OAuth2ApplicationImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the o auth2 application.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(OAuth2Application oAuth2Application) {
		entityCache.removeResult(OAuth2ApplicationModelImpl.ENTITY_CACHE_ENABLED,
			OAuth2ApplicationImpl.class, oAuth2Application.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<OAuth2Application> oAuth2Applications) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (OAuth2Application oAuth2Application : oAuth2Applications) {
			entityCache.removeResult(OAuth2ApplicationModelImpl.ENTITY_CACHE_ENABLED,
				OAuth2ApplicationImpl.class, oAuth2Application.getPrimaryKey());
		}
	}

	/**
	 * Creates a new o auth2 application with the primary key. Does not add the o auth2 application to the database.
	 *
	 * @param oAuth2ApplicationId the primary key for the new o auth2 application
	 * @return the new o auth2 application
	 */
	@Override
	public OAuth2Application create(long oAuth2ApplicationId) {
		OAuth2Application oAuth2Application = new OAuth2ApplicationImpl();

		oAuth2Application.setNew(true);
		oAuth2Application.setPrimaryKey(oAuth2ApplicationId);

		oAuth2Application.setCompanyId(companyProvider.getCompanyId());

		return oAuth2Application;
	}

	/**
	 * Removes the o auth2 application with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param oAuth2ApplicationId the primary key of the o auth2 application
	 * @return the o auth2 application that was removed
	 * @throws NoSuchOAuth2ApplicationException if a o auth2 application with the primary key could not be found
	 */
	@Override
	public OAuth2Application remove(long oAuth2ApplicationId)
		throws NoSuchOAuth2ApplicationException {
		return remove((Serializable)oAuth2ApplicationId);
	}

	/**
	 * Removes the o auth2 application with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the o auth2 application
	 * @return the o auth2 application that was removed
	 * @throws NoSuchOAuth2ApplicationException if a o auth2 application with the primary key could not be found
	 */
	@Override
	public OAuth2Application remove(Serializable primaryKey)
		throws NoSuchOAuth2ApplicationException {
		Session session = null;

		try {
			session = openSession();

			OAuth2Application oAuth2Application = (OAuth2Application)session.get(OAuth2ApplicationImpl.class,
					primaryKey);

			if (oAuth2Application == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOAuth2ApplicationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(oAuth2Application);
		}
		catch (NoSuchOAuth2ApplicationException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected OAuth2Application removeImpl(OAuth2Application oAuth2Application) {
		oAuth2Application = toUnwrappedModel(oAuth2Application);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(oAuth2Application)) {
				oAuth2Application = (OAuth2Application)session.get(OAuth2ApplicationImpl.class,
						oAuth2Application.getPrimaryKeyObj());
			}

			if (oAuth2Application != null) {
				session.delete(oAuth2Application);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (oAuth2Application != null) {
			clearCache(oAuth2Application);
		}

		return oAuth2Application;
	}

	@Override
	public OAuth2Application updateImpl(OAuth2Application oAuth2Application) {
		oAuth2Application = toUnwrappedModel(oAuth2Application);

		boolean isNew = oAuth2Application.isNew();

		OAuth2ApplicationModelImpl oAuth2ApplicationModelImpl = (OAuth2ApplicationModelImpl)oAuth2Application;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (oAuth2Application.getCreateDate() == null)) {
			if (serviceContext == null) {
				oAuth2Application.setCreateDate(now);
			}
			else {
				oAuth2Application.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!oAuth2ApplicationModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				oAuth2Application.setModifiedDate(now);
			}
			else {
				oAuth2Application.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (oAuth2Application.isNew()) {
				session.save(oAuth2Application);

				oAuth2Application.setNew(false);
			}
			else {
				oAuth2Application = (OAuth2Application)session.merge(oAuth2Application);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(OAuth2ApplicationModelImpl.ENTITY_CACHE_ENABLED,
			OAuth2ApplicationImpl.class, oAuth2Application.getPrimaryKey(),
			oAuth2Application, false);

		oAuth2Application.resetOriginalValues();

		return oAuth2Application;
	}

	protected OAuth2Application toUnwrappedModel(
		OAuth2Application oAuth2Application) {
		if (oAuth2Application instanceof OAuth2ApplicationImpl) {
			return oAuth2Application;
		}

		OAuth2ApplicationImpl oAuth2ApplicationImpl = new OAuth2ApplicationImpl();

		oAuth2ApplicationImpl.setNew(oAuth2Application.isNew());
		oAuth2ApplicationImpl.setPrimaryKey(oAuth2Application.getPrimaryKey());

		oAuth2ApplicationImpl.setOAuth2ApplicationId(oAuth2Application.getOAuth2ApplicationId());
		oAuth2ApplicationImpl.setGroupId(oAuth2Application.getGroupId());
		oAuth2ApplicationImpl.setCompanyId(oAuth2Application.getCompanyId());
		oAuth2ApplicationImpl.setUserId(oAuth2Application.getUserId());
		oAuth2ApplicationImpl.setUserName(oAuth2Application.getUserName());
		oAuth2ApplicationImpl.setCreateDate(oAuth2Application.getCreateDate());
		oAuth2ApplicationImpl.setModifiedDate(oAuth2Application.getModifiedDate());
		oAuth2ApplicationImpl.setName(oAuth2Application.getName());
		oAuth2ApplicationImpl.setDescription(oAuth2Application.getDescription());

		return oAuth2ApplicationImpl;
	}

	/**
	 * Returns the o auth2 application with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the o auth2 application
	 * @return the o auth2 application
	 * @throws NoSuchOAuth2ApplicationException if a o auth2 application with the primary key could not be found
	 */
	@Override
	public OAuth2Application findByPrimaryKey(Serializable primaryKey)
		throws NoSuchOAuth2ApplicationException {
		OAuth2Application oAuth2Application = fetchByPrimaryKey(primaryKey);

		if (oAuth2Application == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchOAuth2ApplicationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return oAuth2Application;
	}

	/**
	 * Returns the o auth2 application with the primary key or throws a {@link NoSuchOAuth2ApplicationException} if it could not be found.
	 *
	 * @param oAuth2ApplicationId the primary key of the o auth2 application
	 * @return the o auth2 application
	 * @throws NoSuchOAuth2ApplicationException if a o auth2 application with the primary key could not be found
	 */
	@Override
	public OAuth2Application findByPrimaryKey(long oAuth2ApplicationId)
		throws NoSuchOAuth2ApplicationException {
		return findByPrimaryKey((Serializable)oAuth2ApplicationId);
	}

	/**
	 * Returns the o auth2 application with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the o auth2 application
	 * @return the o auth2 application, or <code>null</code> if a o auth2 application with the primary key could not be found
	 */
	@Override
	public OAuth2Application fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(OAuth2ApplicationModelImpl.ENTITY_CACHE_ENABLED,
				OAuth2ApplicationImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		OAuth2Application oAuth2Application = (OAuth2Application)serializable;

		if (oAuth2Application == null) {
			Session session = null;

			try {
				session = openSession();

				oAuth2Application = (OAuth2Application)session.get(OAuth2ApplicationImpl.class,
						primaryKey);

				if (oAuth2Application != null) {
					cacheResult(oAuth2Application);
				}
				else {
					entityCache.putResult(OAuth2ApplicationModelImpl.ENTITY_CACHE_ENABLED,
						OAuth2ApplicationImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(OAuth2ApplicationModelImpl.ENTITY_CACHE_ENABLED,
					OAuth2ApplicationImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return oAuth2Application;
	}

	/**
	 * Returns the o auth2 application with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param oAuth2ApplicationId the primary key of the o auth2 application
	 * @return the o auth2 application, or <code>null</code> if a o auth2 application with the primary key could not be found
	 */
	@Override
	public OAuth2Application fetchByPrimaryKey(long oAuth2ApplicationId) {
		return fetchByPrimaryKey((Serializable)oAuth2ApplicationId);
	}

	@Override
	public Map<Serializable, OAuth2Application> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, OAuth2Application> map = new HashMap<Serializable, OAuth2Application>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			OAuth2Application oAuth2Application = fetchByPrimaryKey(primaryKey);

			if (oAuth2Application != null) {
				map.put(primaryKey, oAuth2Application);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(OAuth2ApplicationModelImpl.ENTITY_CACHE_ENABLED,
					OAuth2ApplicationImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (OAuth2Application)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_OAUTH2APPLICATION_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(",");
		}

		query.setIndex(query.index() - 1);

		query.append(")");

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (OAuth2Application oAuth2Application : (List<OAuth2Application>)q.list()) {
				map.put(oAuth2Application.getPrimaryKeyObj(), oAuth2Application);

				cacheResult(oAuth2Application);

				uncachedPrimaryKeys.remove(oAuth2Application.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(OAuth2ApplicationModelImpl.ENTITY_CACHE_ENABLED,
					OAuth2ApplicationImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the o auth2 applications.
	 *
	 * @return the o auth2 applications
	 */
	@Override
	public List<OAuth2Application> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the o auth2 applications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OAuth2ApplicationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of o auth2 applications
	 * @param end the upper bound of the range of o auth2 applications (not inclusive)
	 * @return the range of o auth2 applications
	 */
	@Override
	public List<OAuth2Application> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the o auth2 applications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OAuth2ApplicationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of o auth2 applications
	 * @param end the upper bound of the range of o auth2 applications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of o auth2 applications
	 */
	@Override
	public List<OAuth2Application> findAll(int start, int end,
		OrderByComparator<OAuth2Application> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the o auth2 applications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OAuth2ApplicationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of o auth2 applications
	 * @param end the upper bound of the range of o auth2 applications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of o auth2 applications
	 */
	@Override
	public List<OAuth2Application> findAll(int start, int end,
		OrderByComparator<OAuth2Application> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<OAuth2Application> list = null;

		if (retrieveFromCache) {
			list = (List<OAuth2Application>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_OAUTH2APPLICATION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_OAUTH2APPLICATION;

				if (pagination) {
					sql = sql.concat(OAuth2ApplicationModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<OAuth2Application>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<OAuth2Application>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the o auth2 applications from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (OAuth2Application oAuth2Application : findAll()) {
			remove(oAuth2Application);
		}
	}

	/**
	 * Returns the number of o auth2 applications.
	 *
	 * @return the number of o auth2 applications
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_OAUTH2APPLICATION);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return OAuth2ApplicationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the o auth2 application persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(OAuth2ApplicationImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_OAUTH2APPLICATION = "SELECT oAuth2Application FROM OAuth2Application oAuth2Application";
	private static final String _SQL_SELECT_OAUTH2APPLICATION_WHERE_PKS_IN = "SELECT oAuth2Application FROM OAuth2Application oAuth2Application WHERE oAuth2ApplicationId IN (";
	private static final String _SQL_COUNT_OAUTH2APPLICATION = "SELECT COUNT(oAuth2Application) FROM OAuth2Application oAuth2Application";
	private static final String _ORDER_BY_ENTITY_ALIAS = "oAuth2Application.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OAuth2Application exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(OAuth2ApplicationPersistenceImpl.class);
}