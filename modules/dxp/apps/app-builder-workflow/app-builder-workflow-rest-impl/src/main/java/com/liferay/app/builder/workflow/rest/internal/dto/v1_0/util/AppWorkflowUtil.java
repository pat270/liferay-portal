/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.app.builder.workflow.rest.internal.dto.v1_0.util;

import com.liferay.app.builder.model.AppBuilderAppVersion;
import com.liferay.app.builder.workflow.model.AppBuilderWorkflowTaskLink;
import com.liferay.app.builder.workflow.rest.dto.v1_0.AppWorkflow;
import com.liferay.app.builder.workflow.rest.dto.v1_0.AppWorkflowDataLayoutLink;
import com.liferay.app.builder.workflow.rest.dto.v1_0.AppWorkflowRoleAssignment;
import com.liferay.app.builder.workflow.rest.dto.v1_0.AppWorkflowState;
import com.liferay.app.builder.workflow.rest.dto.v1_0.AppWorkflowTask;
import com.liferay.app.builder.workflow.rest.dto.v1_0.AppWorkflowTransition;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.vulcan.util.TransformUtil;
import com.liferay.portal.workflow.kaleo.definition.Definition;
import com.liferay.portal.workflow.kaleo.definition.Node;
import com.liferay.portal.workflow.kaleo.definition.RoleAssignment;
import com.liferay.portal.workflow.kaleo.definition.State;
import com.liferay.portal.workflow.kaleo.definition.Task;
import com.liferay.portal.workflow.kaleo.definition.Transition;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Rafael Praxedes
 */
public class AppWorkflowUtil {

	public static AppWorkflow toAppWorkflow(
		AppBuilderAppVersion appBuilderAppVersion,
		List<AppBuilderWorkflowTaskLink> appBuilderWorkflowTaskLinks,
		Long appWorkflowId, Definition definition,
		Function<Long, Role> roleFunction, Long workflowDefinitionId) {

		return new AppWorkflow() {
			{
				appId = appWorkflowId;
				appVersion = appBuilderAppVersion.getVersion();
				appWorkflowDefinitionId = workflowDefinitionId;

				setAppWorkflowStates(
					() -> {
						List<State> states = new ArrayList<>();

						states.add(definition.getInitialState());
						states.addAll(definition.getTerminalStates());

						return _toAppWorkflowStates(states);
					});
				setAppWorkflowTasks(
					() -> {
						Map<String, List<AppBuilderWorkflowTaskLink>> map =
							Stream.of(
								appBuilderWorkflowTaskLinks
							).flatMap(
								List::stream
							).collect(
								Collectors.groupingBy(
									AppBuilderWorkflowTaskLink::
										getWorkflowTaskName,
									LinkedHashMap::new, Collectors.toList())
							);

						List<AppWorkflowTask> appWorkflowTasks =
							TransformUtil.transform(
								map.entrySet(),
								entry -> _toAppWorkflowTask(
									entry.getValue(),
									definition.getNode(entry.getKey()),
									roleFunction, entry.getKey()));

						return appWorkflowTasks.toArray(new AppWorkflowTask[0]);
					});
			}
		};
	}

	private static AppWorkflowRoleAssignment[] _toAppWorkflowRoleAssignments(
		Function<Long, Role> roleFunction, Task task) {

		return Stream.of(
			task.getAssignments()
		).flatMap(
			Set::stream
		).filter(
			RoleAssignment.class::isInstance
		).map(
			RoleAssignment.class::cast
		).map(
			RoleAssignment::getRoleId
		).map(
			roleFunction
		).filter(
			Objects::nonNull
		).map(
			role -> new AppWorkflowRoleAssignment() {
				{
					roleId = role.getRoleId();
					roleName = role.getName();
				}
			}
		).toArray(
			AppWorkflowRoleAssignment[]::new
		);
	}

	private static AppWorkflowState[] _toAppWorkflowStates(List<State> states) {
		return Stream.of(
			states
		).flatMap(
			List::stream
		).map(
			state -> new AppWorkflowState() {
				{
					appWorkflowTransitions = _toAppWorkflowTransitions(
						state.getOutgoingTransitionsList());
					initial = state.isInitial();
					name = state.getName();
				}
			}
		).toArray(
			AppWorkflowState[]::new
		);
	}

	private static AppWorkflowTask _toAppWorkflowTask(
		List<AppBuilderWorkflowTaskLink> appBuilderWorkflowTaskLinks, Node node,
		Function<Long, Role> roleFunction, String taskName) {

		return new AppWorkflowTask() {
			{
				appWorkflowDataLayoutLinks = TransformUtil.transformToArray(
					appBuilderWorkflowTaskLinks,
					appBuilderWorkflowTaskLink ->
						new AppWorkflowDataLayoutLink() {
							{
								dataLayoutId =
									appBuilderWorkflowTaskLink.
										getDdmStructureLayoutId();
								readOnly =
									appBuilderWorkflowTaskLink.getReadOnly();
							}
						},
					AppWorkflowDataLayoutLink.class);
				appWorkflowRoleAssignments = _toAppWorkflowRoleAssignments(
					roleFunction, (Task)node);
				appWorkflowTransitions = _toAppWorkflowTransitions(
					node.getOutgoingTransitionsList());
				name = taskName;
			}
		};
	}

	private static AppWorkflowTransition[] _toAppWorkflowTransitions(
		List<Transition> transitions) {

		return Stream.of(
			transitions
		).flatMap(
			List::stream
		).map(
			transition -> new AppWorkflowTransition() {
				{
					name = transition.getName();
					primary = transition.isDefault();

					setTransitionTo(
						() -> {
							Node targetNode = transition.getTargetNode();

							return targetNode.getName();
						});
				}
			}
		).toArray(
			AppWorkflowTransition[]::new
		);
	}

}