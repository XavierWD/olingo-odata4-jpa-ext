/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cairone.olingo.ext.jpa.enums;

import org.apache.olingo.server.api.uri.queryoption.expression.BinaryOperatorKind;

/**
 * Enumeration for grouping different kinds of binary operations
 * 
 * @author diego.cairone
 */
public enum BinaryOperatorGroup {
	ARITHMETIC_OPERATOR, LOGICAL_OPERATOR, COMPARISON_OPERATOR, UNKNOWN;
	
	public static BinaryOperatorGroup from(BinaryOperatorKind binaryOperatorKind) {
		switch(binaryOperatorKind) {
		case ADD:
			return ARITHMETIC_OPERATOR;
		case AND:
			return LOGICAL_OPERATOR;
		case DIV:
			return ARITHMETIC_OPERATOR;
		case EQ:
			return COMPARISON_OPERATOR;
		case GE:
			return COMPARISON_OPERATOR;
		case GT:
			return COMPARISON_OPERATOR;
		case HAS:
			return COMPARISON_OPERATOR;
		case LE:
			return COMPARISON_OPERATOR;
		case LT:
			return COMPARISON_OPERATOR;
		case MOD:
			return ARITHMETIC_OPERATOR;
		case MUL:
			return ARITHMETIC_OPERATOR;
		case NE:
			return COMPARISON_OPERATOR;
		case OR:
			return LOGICAL_OPERATOR;
		case SUB:
			return ARITHMETIC_OPERATOR;
		default:
			return UNKNOWN;
		}
	}
}
