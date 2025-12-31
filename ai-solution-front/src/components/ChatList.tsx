import React from 'react';
import { MessageSquarePlus, Search } from 'lucide-react';
import { Chat, Theme, AccentColor } from '../App';

interface ChatListProps {
  chats: Chat[];
  selectedChatId: string;
  onSelectChat: (id: string) => void;
  isOpen: boolean;
  theme: Theme;
  accentColor: AccentColor;
}

export function ChatList({ chats, selectedChatId, onSelectChat, isOpen, theme, accentColor }: ChatListProps) {
  const accentClasses = {
    blue: 'border-l-blue-500',
    purple: 'border-l-purple-500',
    green: 'border-l-green-500',
    pink: 'border-l-pink-500',
    orange: 'border-l-orange-500',
  };

  return (
    <div className={`w-80 border-r flex flex-col transition-all duration-300 ${
      isOpen ? 'translate-x-0' : '-translate-x-full absolute h-full z-10'
    } ${
      theme === 'dark'
        ? 'bg-gray-900 border-gray-800'
        : 'bg-gray-50 border-gray-200'
    }`}>
      {/* Header */}
      <div className={`p-4 border-b ${
        theme === 'dark' ? 'border-gray-800' : 'border-gray-200'
      }`}>
        <div className="flex items-center justify-between mb-4">
          <h1 className={theme === 'dark' ? 'text-white' : 'text-gray-900'}>
            채팅
          </h1>
          <button className={`p-2 rounded-lg transition-colors ${
            theme === 'dark'
              ? 'hover:bg-gray-800 text-gray-400'
              : 'hover:bg-gray-200 text-gray-600'
          }`}>
            <MessageSquarePlus className="w-5 h-5" />
          </button>
        </div>
        
        {/* Search */}
        <div className="relative">
          <Search className={`w-4 h-4 absolute left-3 top-1/2 transform -translate-y-1/2 ${
            theme === 'dark' ? 'text-gray-500' : 'text-gray-400'
          }`} />
          <input
            type="text"
            placeholder="검색..."
            className={`w-full pl-10 pr-4 py-2 rounded-lg border focus:outline-none focus:ring-2 focus:ring-${accentColor}-500 ${
              theme === 'dark'
                ? 'bg-gray-800 border-gray-700 text-white placeholder:text-gray-500'
                : 'bg-white border-gray-200 text-gray-900 placeholder:text-gray-400'
            }`}
          />
        </div>
      </div>

      {/* Chat List */}
      <div className="flex-1 overflow-y-auto">
        {chats.map((chat) => (
          <button
            key={chat.id}
            onClick={() => onSelectChat(chat.id)}
            className={`w-full p-4 text-left transition-all border-b ${
              theme === 'dark'
                ? 'hover:bg-gray-800 border-gray-800'
                : 'hover:bg-gray-100 border-gray-100'
            } ${
              selectedChatId === chat.id 
                ? `border-l-4 ${accentClasses[accentColor]} ${
                    theme === 'dark' ? 'bg-gray-800' : 'bg-gray-100'
                  }` 
                : ''
            }`}
          >
            <div className="flex justify-between items-start mb-1">
              <h3 className={`flex-1 truncate pr-2 ${
                theme === 'dark' ? 'text-white' : 'text-gray-900'
              }`}>
                {chat.title}
              </h3>
              <span className={`text-sm whitespace-nowrap ${
                theme === 'dark' ? 'text-gray-500' : 'text-gray-500'
              }`}>
                {chat.timestamp}
              </span>
            </div>
            <p className={`text-sm truncate ${
              theme === 'dark' ? 'text-gray-400' : 'text-gray-600'
            }`}>
              {chat.preview}
            </p>
          </button>
        ))}
      </div>
    </div>
  );
}
