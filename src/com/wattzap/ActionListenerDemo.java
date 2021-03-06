/* This file is part of Wattzap Community Edition.
 *
 * Wattzap Community Edtion is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Wattzap Community Edition is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Wattzap.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.wattzap;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple ActionListener demo without all the Swing Graphics Code. This is an
 * example of the GoF Observer pattern used in Swing for inter-object
 * communication. When using Swing, there is a choice among using Observable,
 * Observer , or the many XXXListener interfaces. The advantage of the Listener
 * implementation is the Model doesn't need to extend the concrete Observable
 * class.
 * <p>
 * If you have an object that needs to share it's state with others, without
 * knowing who those objects are, this pattern is what you need.
 * 
 * @author David George
 * @date 30 May 2013
 */
public class ActionListenerDemo {
	MyView view;

	MyModel model;

	public ActionListenerDemo() {

		view = new MyView();

		model = new MyModel();
		model.addActionListener(view);

	}

	public static void main(String[] av) {
		ActionListenerDemo me = new ActionListenerDemo();
		me.demo();
	}

	public void demo() {
		for (int i = 0; i < 100; i++) {
			model.changed();
		}
	}

	/** The Observer normally maintains a view on the data */
	class MyView implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {


		}

	}

	/** The Observable normally maintains the data */
	class MyModel {
		private List<ActionListener> actionListeners = new ArrayList<ActionListener>();

		private int speed = 0;;

		public int getSpeed() {
			return speed;
		}

		public void changed() {
			speed++;
			for (ActionListener l : actionListeners) {
				l.actionPerformed(new ActionEvent(this, speed, "Speed Changed"));
			}
		}

		public void addActionListener(ActionListener l) {
			actionListeners.add(l);
		}
	}
}