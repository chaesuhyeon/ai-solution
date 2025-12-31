import React, { useState, useRef, useEffect } from 'react';
import { Send, Menu, Paperclip, X, Sun, Moon, Palette } from 'lucide-react';
import { Message, Theme, AccentColor } from '../App';
import { MessageBubble } from './MessageBubble';

interface ChatWindowProps {
  messages: Message[];
  onSendMessage: (content: string) => void;
  onToggleSidebar: () => void;
  theme: Theme;
  accentColor: AccentColor;
  onThemeChange: (theme: Theme) => void;
  onAccentColorChange: (color: AccentColor) => void;
}

const accentColors = {
  blue: {
    bg: 'bg-blue-600',
    hover: 'hover:bg-blue-700',
    ring: 'ring-blue-500',
    text: 'text-blue-600',
    light: 'bg-blue-50',
    border: 'border-blue-500',
  },
  purple: {
    bg: 'bg-purple-600',
    hover: 'hover:bg-purple-700',
    ring: 'ring-purple-500',
    text: 'text-purple-600',
    light: 'bg-purple-50',
    border: 'border-purple-500',
  },
  green: {
    bg: 'bg-green-600',
    hover: 'hover:bg-green-700',
    ring: 'ring-green-500',
    text: 'text-green-600',
    light: 'bg-green-50',
    border: 'border-green-500',
  },
  pink: {
    bg: 'bg-pink-600',
    hover: 'hover:bg-pink-700',
    ring: 'ring-pink-500',
    text: 'text-pink-600',
    light: 'bg-pink-50',
    border: 'border-pink-500',
  },
  orange: {
    bg: 'bg-orange-600',
    hover: 'hover:bg-orange-700',
    ring: 'ring-orange-500',
    text: 'text-orange-600',
    light: 'bg-orange-50',
    border: 'border-orange-500',
  },
};

export function ChatWindow({ 
  messages, 
  onSendMessage, 
  onToggleSidebar, 
  theme, 
  accentColor,
  onThemeChange,
  onAccentColorChange 
}: ChatWindowProps) {
  const [input, setInput] = useState('');
  const [selectedModel, setSelectedModel] = useState('gpt-4');
  const [attachedFiles, setAttachedFiles] = useState<File[]>([]);
  const [showColorPicker, setShowColorPicker] = useState(false);
  const messagesEndRef = useRef<HTMLDivElement>(null);
  const fileInputRef = useRef<HTMLInputElement>(null);

  const scrollToBottom = () => {
    messagesEndRef.current?.scrollIntoView({ behavior: 'smooth' });
  };

  useEffect(() => {
    scrollToBottom();
  }, [messages]);

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (input.trim()) {
      onSendMessage(input);
      setInput('');
      setAttachedFiles([]);
    }
  };

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files) {
      setAttachedFiles([...attachedFiles, ...Array.from(e.target.files)]);
    }
  };

  const removeFile = (index: number) => {
    setAttachedFiles(attachedFiles.filter((_, i) => i !== index));
  };

  const colorConfig = accentColors[accentColor];

  return (
    <div className={`flex-1 flex flex-col ${
      theme === 'dark' ? 'bg-gray-900' : 'bg-white'
    }`}>
      {/* Header */}
      <div className={`p-4 border-b flex items-center justify-between ${
        theme === 'dark' 
          ? 'bg-gray-900 border-gray-800' 
          : 'bg-white border-gray-200'
      }`}>
        <div className="flex items-center gap-3">
          <button
            onClick={onToggleSidebar}
            className={`p-2 rounded-lg transition-colors ${
              theme === 'dark'
                ? 'hover:bg-gray-800 text-gray-300'
                : 'hover:bg-gray-100 text-gray-600'
            }`}
          >
            <Menu className="w-5 h-5" />
          </button>
          <div>
            <h2 className={theme === 'dark' ? 'text-white' : 'text-gray-900'}>
              AI 어시스턴트
            </h2>
            <p className={`text-sm ${
              theme === 'dark' ? 'text-gray-400' : 'text-gray-600'
            }`}>
              무엇이든 물어보세요
            </p>
          </div>
        </div>

        {/* Theme and Color Controls */}
        <div className="flex items-center gap-2">
          <button
            onClick={() => onThemeChange(theme === 'dark' ? 'light' : 'dark')}
            className={`p-2 rounded-lg transition-colors ${
              theme === 'dark'
                ? 'hover:bg-gray-800 text-gray-300'
                : 'hover:bg-gray-100 text-gray-600'
            }`}
            title={theme === 'dark' ? '라이트 모드' : '다크 모드'}
          >
            {theme === 'dark' ? <Sun className="w-5 h-5" /> : <Moon className="w-5 h-5" />}
          </button>

          <div className="relative">
            <button
              onClick={() => setShowColorPicker(!showColorPicker)}
              className={`p-2 rounded-lg transition-colors ${
                theme === 'dark'
                  ? 'hover:bg-gray-800 text-gray-300'
                  : 'hover:bg-gray-100 text-gray-600'
              }`}
              title="강조 색상 변경"
            >
              <Palette className="w-5 h-5" />
            </button>

            {showColorPicker && (
              <div className={`absolute right-0 mt-2 p-3 rounded-lg shadow-lg border z-10 ${
                theme === 'dark'
                  ? 'bg-gray-800 border-gray-700'
                  : 'bg-white border-gray-200'
              }`}>
                <div className="flex gap-2">
                  {(Object.keys(accentColors) as AccentColor[]).map((color) => (
                    <button
                      key={color}
                      onClick={() => {
                        onAccentColorChange(color);
                        setShowColorPicker(false);
                      }}
                      className={`w-8 h-8 rounded-full ${accentColors[color].bg} ${
                        accentColor === color ? 'ring-2 ring-offset-2 ring-gray-400' : ''
                      }`}
                      title={color}
                    />
                  ))}
                </div>
              </div>
            )}
          </div>
        </div>
      </div>

      {/* Messages */}
      <div className="flex-1 overflow-y-auto p-6 space-y-6">
        {messages.map((message) => (
          <MessageBubble 
            key={message.id} 
            message={message} 
            theme={theme}
            accentColor={accentColor}
          />
        ))}
        <div ref={messagesEndRef} />
      </div>

      {/* Input */}
      <div className={`border-t p-4 ${
        theme === 'dark'
          ? 'bg-gray-900 border-gray-800'
          : 'bg-white border-gray-200'
      }`}>
        {/* Attached Files */}
        {attachedFiles.length > 0 && (
          <div className="mb-3 flex flex-wrap gap-2">
            {attachedFiles.map((file, index) => (
              <div
                key={index}
                className={`flex items-center gap-2 px-3 py-2 rounded-full text-sm ${
                  theme === 'dark'
                    ? 'bg-gray-800 text-gray-300'
                    : 'bg-gray-100 text-gray-700'
                }`}
              >
                <span className="truncate max-w-[150px]">{file.name}</span>
                <button
                  onClick={() => removeFile(index)}
                  className={`rounded-full p-0.5 transition-colors ${
                    theme === 'dark'
                      ? 'hover:bg-gray-700'
                      : 'hover:bg-gray-200'
                  }`}
                >
                  <X className="w-3 h-3" />
                </button>
              </div>
            ))}
          </div>
        )}
        
        <form onSubmit={handleSubmit} className="flex gap-3">
          <select
            value={selectedModel}
            onChange={(e) => setSelectedModel(e.target.value)}
            className={`px-4 py-3 rounded-xl border-2 focus:outline-none focus:ring-2 cursor-pointer transition-all ${
              theme === 'dark'
                ? 'bg-gray-800 border-gray-700 text-gray-300'
                : 'bg-gray-50 border-gray-200 text-gray-900'
            } focus:${colorConfig.ring}`}
          >
            <option value="gpt-4">GPT-4</option>
            <option value="gpt-3.5-turbo">GPT-3.5</option>
            <option value="claude-3">Claude 3</option>
            <option value="gemini-pro">Gemini Pro</option>
          </select>
          
          <div className={`flex-1 flex items-center gap-2 px-4 py-3 rounded-xl border-2 transition-all ${
            theme === 'dark'
              ? 'bg-gray-800 border-gray-700'
              : 'bg-gray-50 border-gray-200'
          } focus-within:ring-2 focus-within:${colorConfig.ring}`}>
            <input
              type="text"
              value={input}
              onChange={(e) => setInput(e.target.value)}
              placeholder="메시지를 입력하세요..."
              className={`flex-1 bg-transparent focus:outline-none ${
                theme === 'dark'
                  ? 'text-white placeholder:text-gray-500'
                  : 'text-gray-900 placeholder:text-gray-400'
              }`}
            />
            <input
              ref={fileInputRef}
              type="file"
              multiple
              onChange={handleFileChange}
              className="hidden"
            />
            <button
              type="button"
              onClick={() => fileInputRef.current?.click()}
              className={`p-2 rounded-lg transition-colors ${
                theme === 'dark'
                  ? 'hover:bg-gray-700 text-gray-400'
                  : 'hover:bg-gray-200 text-gray-600'
              }`}
            >
              <Paperclip className="w-4 h-4" />
            </button>
          </div>
          
          <button
            type="submit"
            disabled={!input.trim()}
            className={`px-6 py-3 text-white rounded-xl transition-all disabled:opacity-50 disabled:cursor-not-allowed flex items-center gap-2 ${
              colorConfig.bg
            } ${colorConfig.hover}`}
          >
            <Send className="w-4 h-4" />
            전송
          </button>
        </form>
      </div>
    </div>
  );
}
