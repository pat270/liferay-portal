/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

import {useContext, useEffect} from 'react';

import {FilterContext} from '../../../shared/components/filter/FilterContext.es';
import filterConstants from '../../../shared/components/filter/util/filterConstants.es';
import {useBeforeUnload} from '../../../shared/hooks/useBeforeUnload.es';
import {useSessionStorage} from '../../../shared/hooks/useStorage.es';
import {AppContext} from '../../AppContext.es';

const useTimeRangeFetch = () => {
	const {client} = useContext(AppContext);
	const {dispatchFilterError} = useContext(FilterContext);
	const [, update, remove] = useSessionStorage('timeRanges');

	const clean = () => {
		dispatchFilterError(filterConstants.timeRange.key, true);
		remove();
	};

	useBeforeUnload(clean);

	useEffect(() => {
		client
			.get('/time-ranges')
			.then(({data}) => {
				update({items: data.items});
			})
			.catch(() => {
				dispatchFilterError(filterConstants.timeRange.key);
			});

		return clean;
		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, []);
};

export {useTimeRangeFetch};
