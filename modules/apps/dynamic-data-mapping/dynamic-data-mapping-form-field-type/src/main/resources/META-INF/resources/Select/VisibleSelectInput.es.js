/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import './VisibleSelectInput.scss';

import ClayButton from '@clayui/button';
import ClayLabel from '@clayui/label';
import classNames from 'classnames';
import React, {forwardRef} from 'react';

const LabelOptionListItem = ({onCloseButtonClicked, option, readOnly}) => (
	<li>
		<ClayLabel
			className="ddm-select-option-label"
			closeButtonProps={{
				'data-testid': `closeButton${option.value}`,
				'onClick': (event) => {
					event.preventDefault();
					event.stopPropagation();

					onCloseButtonClicked({event, value: option.value});
				},
			}}
			value={option.value}
			withClose={!readOnly}
		>
			{option.label}
		</ClayLabel>
	</li>
);

const OptionSelected = ({isPlaceholder, label}) => (
	<div
		className={classNames('option-selected', {
			'option-selected-placeholder': isPlaceholder,
		})}
	>
		{label}
	</div>
);

const VisibleSelectInput = forwardRef(
	(
		{
			expanded,
			id,
			multiple,
			onClick,
			onCloseButtonClicked,
			onKeyDown,
			options,
			readOnly,
			value,
		},
		ref
	) => {
		const triggerPlaceholder = multiple
			? Liferay.Language.get('choose-options')
			: Liferay.Language.get('choose-an-option');

		const isValueEmpty = !value.length;

		const selectedLabel = () => {
			if (isValueEmpty) {
				return triggerPlaceholder;
			}

			const selectedOption = options.find(
				(option) => option.value === value[0]
			);

			return selectedOption ? selectedOption.label : triggerPlaceholder;
		};

		return (
			<ClayButton
				aria-expanded={expanded}
				aria-haspopup="true"
				className="form-control form-control-select lfr__ddm-select-input-trigger"
				displayType={null}
				id={id}
				onClick={onClick}
				onKeyDown={onKeyDown}
				ref={ref}
				type="button"
			>
				{isValueEmpty || (value.length === 1 && !multiple) ? (
					<OptionSelected
						isPlaceholder={isValueEmpty}
						label={selectedLabel()}
					/>
				) : (
					<>
						{value.map((item) => {
							const option = options.find(
								(option) => option.value === item
							);

							return (
								<>
									{option && (
										<LabelOptionListItem
											key={`${option.value}-${option.label}`}
											onCloseButtonClicked={
												onCloseButtonClicked
											}
											option={option}
											readOnly={readOnly}
										/>
									)}
								</>
							);
						})}
					</>
				)}
			</ClayButton>
		);
	}
);

export default VisibleSelectInput;
