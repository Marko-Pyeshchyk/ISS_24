/* Generated by AN DISI Unibo */ 
package it.unibo.cleaner24

import it.unibo.kactor.*
import alice.tuprolog.*
import unibo.basicomm23.*
import unibo.basicomm23.interfaces.*
import unibo.basicomm23.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import it.unibo.kactor.sysUtil.createActor   //Sept2023

//User imports JAN2024
import main.resources.robotvirtual.VrobotLLMoves24
import main.resources.map.RoomMap
import main.resources.map.RobotDir
import main.resources.map.RobotDir.Direction

class Cleaner24 ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "activate"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		 val vr = VrobotLLMoves24.create("localhost",myself)
		
			var GoingDown   = true 
		    lateinit var map : RoomMap
			var PX = 0   //Robot pos along X
			var PY = 0   //Robot pos along Y
		return { //this:ActionBasciFsm
				state("activate") { //this:State
					action { //it:State
						  GoingDown = true  
						  RobotDir.setDir(Direction.DOWN)   
						  map = RoomMap.loadRoomMap("map")  
						  map.clear()     
						  map.setCellClean(PX,PY)   
						  map.setRobot(PX,PY)  
						  map.showMap()   
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="coverColumn", cond=doswitch() )
				}	 
				state("coverColumn") { //this:State
					action { //it:State
						 map.setCellClean(PX,PY)   
						delay(300) 
						 var RSTEP = vr.step(350)  
						if(  RSTEP  
						 ){ if(GoingDown) PX++; else PX--  
						 map.setCellClean(PX,PY)   
						forward("stepdone", "stepdone(1)" ,name ) 
						}
						else
						 {forward("stepfail", "stepfail(1)" ,name ) 
						 }
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t00",targetState="coverColumn",cond=whenDispatch("stepdone"))
					transition(edgeName="t01",targetState="turn",cond=whenDispatch("stepfail"))
					interrupthandle(edgeName="t02",targetState="doPause",cond=whenDispatch("pause"),interruptedStateTransitions)
				}	 
				state("turn") { //this:State
					action { //it:State
						CommUtils.outblack("turn while GoingDown=$GoingDown")
						if(  GoingDown  
						 ){ vr.backward(50)   
						 vr.turnLeft()     
						}
						else
						 { vr.turnRight()  
						 }
						delay(300) 
						 var RSTEP = vr.step(350)  
						if(  RSTEP  
						 ){forward("stepdone", "stepdone(1)" ,name ) 
						}
						else
						 {forward("stepfail", "stepfail(1)" ,name ) 
						 }
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t03",targetState="stepAfterTurn",cond=whenDispatch("stepdone"))
					transition(edgeName="t04",targetState="lastColumn",cond=whenDispatch("stepfail"))
				}	 
				state("stepAfterTurn") { //this:State
					action { //it:State
						CommUtils.outmagenta("stepAfterTurn while GoingDown=$GoingDown")
						  PY++; 
						    		map.setCellClean(PX,PY)  
						if(  GoingDown  
						 ){ vr.turnLeft()    
						}
						else
						 { vr.turnRight()  
						 }
						delay(300) 
						  GoingDown = ! GoingDown 
						   			if( GoingDown ) RobotDir.setDir(Direction.DOWN) 
						   			else RobotDir.setDir(Direction.UP) 
						 var RSTEP = vr.step(350)  
						if(  RSTEP  
						 ){ if(GoingDown) PX++; else PX--  
						 map.setCellClean(PX,PY)   
						 map.showMap()             
						forward("stepdone", "stepdone(1)" ,name ) 
						}
						else
						 {forward("stepfail", "stepfail(1)" ,name ) 
						 }
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t05",targetState="coverColumn",cond=whenDispatch("stepdone"))
					transition(edgeName="t06",targetState="lastColumn",cond=whenDispatch("stepfail"))
				}	 
				state("lastColumn") { //this:State
					action { //it:State
						CommUtils.outmagenta("$name in ${currentState.stateName} | $currentMsg | ${Thread.currentThread().getName()} n=${Thread.activeCount()}")
						 	   
						 map.showMap()   
						  PY++; 
						    		map.setCellClean(PX,PY)  
						if(  GoingDown  
						 ){ vr.turnLeft()     
						}
						else
						 { vr.turnRight()  
						 }
						 map.showMap()             
						  GoingDown = ! GoingDown 
						   			if( GoingDown ) RobotDir.setDir(Direction.DOWN) 
						   			else RobotDir.setDir(Direction.UP) 
						 var RSTEP = vr.step(350)  
						if(  RSTEP  
						 ){forward("stepdone", "stepdone(1)" ,name ) 
						}
						else
						 {forward("stepfail", "stepfail(1)" ,name ) 
						 }
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t07",targetState="coverLastColumn",cond=whenDispatch("stepdone"))
					transition(edgeName="t08",targetState="endofwork",cond=whenDispatch("stepfail"))
				}	 
				state("coverLastColumn") { //this:State
					action { //it:State
						CommUtils.outyellow("coverLastColumn norobot")
						 if(GoingDown) PX++; else PX--  
						 map.setCellClean(PX,PY)   
						delay(200) 
						 var RSTEP = vr.step(350)  
						if(  RSTEP  
						 ){forward("stepdone", "stepdone(1)" ,name ) 
						}
						else
						 {forward("stepfail", "stepfail(1)" ,name ) 
						 }
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t09",targetState="coverLastColumn",cond=whenDispatch("stepdone"))
					transition(edgeName="t010",targetState="endofwork",cond=whenDispatch("stepfail"))
				}	 
				state("endofwork") { //this:State
					action { //it:State
						CommUtils.outblue("---------------------------")
						 map.showMap()   
						 val MS  = map.toString()   
						 map.saveRoomMap("mapcleaned", MS )  
						 System.exit(0)  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
				}	 
				state("fatalError") { //this:State
					action { //it:State
						CommUtils.outred("$name in ${currentState.stateName} | $currentMsg | ${Thread.currentThread().getName()} n=${Thread.activeCount()}")
						 	   
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
				}	 
				state("doPause") { //this:State
					action { //it:State
						CommUtils.outmagenta("PAUSEEEEEEE")
						delay(1000) 
						returnFromInterrupt(interruptedStateTransitions)
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
				}	 
			}
		}
} 